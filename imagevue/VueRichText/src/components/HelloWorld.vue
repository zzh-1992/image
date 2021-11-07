<template>
  <div id="richText">
    <center>
      <h1>Dev playground</h1>
    </center>
    <div  class="tab-button-wrap richText">
      <button :class="{ on: state.curTheme === 'snow' }" @click="switchTheme('snow')">Theme snow</button>
      <button :class="{ on: state.curTheme === 'bubble' }" @click="switchTheme('bubble')">Theme bubble</button>

      <button  @click="downloadRichText()">下载富文本</button>
    </div>
    <quill-editor
        v-if="state.showEditor"
        v-model:value="state.content"
        :options="state.editorOption"
        :disabled="state.disabled"
        @change="onEditorChange($event)"
    />
  </div>

</template>

<script>

import axios from "axios";

import { reactive } from 'vue'
import { /*Quill,*/ quillEditor } from 'vue3-quill'
// console.log(Quill)

//import ImageUploader from "quill-image-uploader"
//Quill.register("modules/imageUploader", ImageUploader)

export default {
  name: 'App',
  components: {
    quillEditor
  },
  setup() {
    const state = reactive({
      curTheme: 'snow',
      showEditor: true,
      content: '',
      editorOption: {
        placeholder: 'core',
        modules: {
          /*imageUploader: {
            upload: (file) => {
              console.log(file)
              return new Promise((resolve, reject) => {
                console.log(reject)
                setTimeout(() => {
                  resolve(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/JavaScript-logo.png/480px-JavaScript-logo.png"
                  );
                }, 3500);
              });
            },
          },*/
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'],
            ['blockquote', 'code-block'],
            [{ header: 1 }, { header: 2 }],
            [{ list: 'ordered' }, { list: 'bullet' }],
            [{ script: 'sub' }, { script: 'super' }],
            [{ indent: '-1' }, { indent: '+1' }],
            [{ direction: 'rtl' }],
            [{ size: ['small', false, 'large', 'huge'] }],
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            [{ color: [] }, { background: [] }],
            [{ font: [] }],
            [{ align: [] }],
            ['clean'],
            ['link', 'image', 'video']
          ]
        }
      },
      disabled: true
    })

    const reinitEditor = () => {
      state.showEditor = false
      setTimeout(() => {
        state.showEditor = true
      })
    }

    const switchTheme = (theme) => {
      reinitEditor()
      state.curTheme = theme
      state.editorOption.theme = theme
    }

    setTimeout(() => {
      state.content = ''
    }, 20)
    setTimeout(() => {
      state.content = ''
    }, 40)
    setTimeout(() => {
      state.disabled = false
    }, 60)

    return { state, switchTheme }
  },
  methods:{
    onEditorChange() {
      // 内容改变时调用接口保存
      var data = {'content': this.state.content}
      axios
          .post('/saveMd', data)
          .then(response => {
            console.log(response)
          })
          .catch(err => {
            console.log(err)
          })
      console.log(this.content);
    },
    downloadRichText(){
      axios
          .post('/downloadRichText')
          .then(response => {
            this.state.content =  response.data
            console.log("res:" + response.data)
          })
          .catch(err => {
            console.log(err)
          })
    }
  }
}
</script>

<style>
#richText {
  width: 1600px;
  height: 720px;
  margin:0 auto;
}
.tab-button-wrap {
  display: flex;
  justify-content:center;
  padding: 15px 0;
}
.tab-button-wrap button{
  padding: 15px 20px;
  border: 1px solid #efefef;
  background: #fff;
  -webkit-appearance: none;
  cursor: pointer;
}
.tab-button-wrap button.on, .tab-button-wrap button:hover{
  background: #efefef;
}
.tab-button-wrap button:active, .tab-button-wrap button:focus{
  border: none;
  outline: none;
}
</style>
