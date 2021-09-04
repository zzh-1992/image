相关帮助文档(感谢热心网友的无私奉献)

*  [前端js/vue下载后台传过来的流文件(如excel)并设置下载文件名](https://segmentfault.com/a/1190000020540788)

*  [apache Excel (HSSF/XSSF)官网教程](http://poi.apache.org/components/spreadsheet/quick-guide.html)

#java_后端
##加入excel依赖
```xml
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>3.17</version>
    </dependency>
```
##springboot 属性配置 
```properties
# 定义文件上传大小
spring.servlet.multipart.max-file-size=5242880
spring.servlet.multipart.max-request-size=15728640
```
##java 处理类
### 上传
```java
@RequestMapping("/fileUpload")
    public AjaxResult fileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        // 获取取消文件后缀的文件名(excel.xlsx)
        String originalFilename = file.getOriginalFilename();

        // 获取输入流
        InputStream inputStream = file.getInputStream();

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //TODO 文件校验

        // 输出文件
        OutputStream os = new FileOutputStream("/Users/grpae/Desktop/" + originalFilename);
        workbook.write(os);
        os.flush();
        os.close();
        workbook.close();

        return AjaxResult.success();
    }
```
### 下载 excel
```java
@RequestMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        // 创建excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建工作页
        XSSFSheet sheet = workbook.createSheet();
        // 设置第一列
        XSSFRow row = sheet.createRow(0);
        XSSFCell c0 = row.createCell(0);
        c0.setCellValue("Name");
        XSSFCell c1 = row.createCell(1);
        c1.setCellValue("Job");
        XSSFCell c2 = row.createCell(2);
        c2.setCellValue("Phone");

        // 模拟数据
        Grape a = new Grape("aaa", "driver", "1363636363636");
        Grape b = new Grape("bbb", "programer", "1363636363699");
        Grape c = new Grape("ccc", "student", "1363636363688");
        Grape a1 = new Grape("aaa", "driver", "1363636363636");
        Grape b2 = new Grape("bbb", "programer", "1363636363699");
        Grape c3 = new Grape("ccc", "student", "1363636363688");
        List<Grape> list = Arrays.asList(a, b, c, a1, b2, c3);

        // 将数据转换到excel文件
        for (int i = 0; i < list.size(); i++) {
            Grape grape = list.get(i);
            XSSFRow rowi = sheet.createRow(i + 1);

            XSSFCell ci0 = rowi.createCell(0);
            ci0.setCellValue(grape.getName());
            XSSFCell ci1 = rowi.createCell(1);
            ci1.setCellValue(grape.getJob());
            XSSFCell ci2 = rowi.createCell(2);
            ci2.setCellValue(grape.getPhone());
        }

        // 文件名
        String fileName = "下载.xls";

        response.setHeader("Content-type", "application/vnd.ms-excel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=ffffff.xlsx");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

@Data
@AllArgsConstructor
@NoArgsConstructor
class Grape {
    String name;
    String job;
    String phone;
}    
```
### 下载excel模版
```java
@RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws UnsupportedEncodingException {
        //文件路径
        String path = "/Users/grape/.../controller/Template.xlsx";

        // 设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String("Template".getBytes("UTF-8"), "iso-8859-1"));
        try {
            // 打开本地文件流
            InputStream inputStream = new FileInputStream(path);
            // 激活下载操作
            OutputStream os = response.getOutputStream();

            // 循环写入输出流 10KB
            byte[] b = new byte[10 * 1024 * 8];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

# vue_前端
```vue
<template>
  <div class="cancelRequest">
    <div>文件：<input type="file" ref="file"></div>
    <button class="btn btn-primary" @click="fileUpload">上传</button>

    <button class="btn btn-success" @click="downloadTemplate">下载模版</button>

    <button class="btn btn-success" @click="download">下载1</button>

    <button class="btn btn-success" @click="download">下载2</button>
  </div>
</template>

<script>

import axios from "axios";
import fileDownload from 'js-file-download'

export default {
  data() {
    return {
      value: ''
    }
  },
  components: {},
  props: {},
  watch: {},
  computed: {},
  methods: {
    fileUpload() {
      let formData = new FormData();
      formData.append('file', this.$refs.file.files[0])
      axios
        .post('/fileUpload', formData, {
          'Content-Type': 'multipart/form-data'
        }).then((response) => {
        if (response.data.code === "1") {
          window.alert("上传成功")
        } else {
          window.alert(response.data.msg)
        }
      })
        .catch(err => {
          console.log("调用出错:" + err);
        });
    },

    download() {
      axios.post("/download", {}, {responseType: 'arraybuffer'}).then(response => {
        let blob = new Blob([response.data], {type: 'application/vnd.ms-excel'})
        let dateTime = new Date()
        let dateTimeStr = dateTime.getFullYear() + '-' + dateTime.getMonth() + '-' + dateTime.getDay()
        let filename = '下载测试-' + dateTimeStr + '_' + new Date().getTime() + '.xlsx'
        if (typeof window.navigator.msSaveBlob !== 'undefined') {
          window.navigator.msSaveBlob(blob, filename)
        } else {
          var blobURL = window.URL.createObjectURL(blob)// 将blob对象转为一个URL
          var tempLink = document.createElement('a')// 创建一个a标签
          tempLink.style.display = 'none'
          tempLink.href = blobURL
          tempLink.setAttribute('download', filename)// 给a标签添加下载属性
          if (typeof tempLink.download === 'undefined') {
            tempLink.setAttribute('target', '_blank')
          }
          document.body.appendChild(tempLink)// 将a标签添加到body当中
          tempLink.click()// 启动下载
          document.body.removeChild(tempLink)// 下载完毕删除a标签
          window.URL.revokeObjectURL(blobURL)
        }
      })
    },

    downloadTemplate() {
      axios.post("/downloadTemplate", {}, {responseType: 'arraybuffer'}).then(res => {
        fileDownload(res.data, 'template.xlsx')
      })
    },

    download2() {
      axios.post("/download", {}, {responseType: 'arraybuffer'}).then(res => {
        fileDownload(res.data, 'xxx.xlsx')
      })
    }
  },
  created() {
  },
  mounted() {
  }
}
</script>
<style>

</style>

```

