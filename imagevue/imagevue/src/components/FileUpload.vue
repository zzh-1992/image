<template>
  <div class="cancelRequest">
    <div>文件：<input type="file" ref="file"></div>
    <button class="btn btn-primary" @click="fileUpload">上传</button>

    <button class="btn btn-success" @click="downloadTemplate">下载模版</button>

    <button class="btn btn-success" @click="download1">下载1</button>

    <button class="btn btn-success" @click="download2">下载2</button>
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

    download1() {
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
