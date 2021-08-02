<template>
  <div>
    <div class="cotn_principal">
      <div class="cont_centrar">
        <div class="cont_login">
          <div class="cont_info_log_sign_up">
            <div class="col_md_login">
              <div class="cont_ba_opcitiy">
                <h2>LOGIN WINDOW</h2>
                <p>Lorem ipsum dolor sit amet, consectetur.</p>
                <button class="btn_login" v-on:click="cambiar_login">LOGIN</button>
              </div>
            </div>
            <div class="col_md_sign_up">
              <div class="cont_ba_opcitiy">
                <h2>SIGN UP WINDOW</h2>
                <p>Lorem ipsum dolor sit amet, consectetur.</p>
                <button class="btn_sign_up" v-on:click="cambiar_sign_up">SIGN UP</button>
              </div>
            </div>
          </div>
          <div class="cont_back_info">
            <div class="cont_img_back_grey"><img :src="po" alt=""/></div>
          </div>
          <div class="cont_forms">
            <div class="cont_img_back_"><img :src="po" alt=""/></div>
            <div class="cont_form_login"><a href="#" v-on:click="ocultar_login_sign_up"><i
              class="material-icons">&#xE5C4;</i></a>
              <h2>LOGIN WINDOW</h2>

              <input id="loginPhone" ref="loginPhone" type="text" placeholder="手机号" onblur="checkPhone"/>
              <h5 id="loginPhoneText"></h5>

              <input id="loginPassword" ref="loginPassword" type="password" placeholder="密码" onblur="checkPassword()"/>
              <h5 id="loginPasswordText"></h5>


              <input id=loginNote ref="loginNote" type="text" placeholder="短信验证码" onblur="checkNote"/>
              <h5 id=loginNoteText></h5>


              <button id="noteButton" @click="getNote" class="note_login">点击获取服务验证码</button>

              <button id="loginButton" @click="login" class="btn_login2">LOGIN</button>
            </div>
            <div class="cont_form_sign_up"><a href="#" v-on:click="ocultar_login_sign_up"><i class="material-icons">&#xE5C4;</i></a>
              <h2>SIGN UP WINDOW</h2>
              <input id="phone" ref="phone" type="text" placeholder="phone"/>
              <input id="password" ref="password" type="password" placeholder="password"/>

              <input id="nickName" ref="nickName" type="text" placeholder="nickName"/>
              <input id="email" ref="email" type="text" placeholder="email"/>

              <input id="captcha" ref="resoult" type="text" class="yzm" placeholder="click down pitcher to refresh"/>

              <input id="questionId" ref="questionId" type="hidden" class="hide-icon" placeholder="questionId"/>
              <img :src="codeUrl" id="imgCode" v-on:click="getCapture" class="localimg"
                   style="cursor:pointer;border:0; display:inline;vertical-align:middle;"/>

              <button id="signup" v-on:click="signup" class="btn_sign_up">SIGN UP</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
  </div>
</template>


<script>

// 导入静态图片
import po from '../assets/login-background.jpg'

export default {
  name:"Signup",
  beforeDestroy() {
    console.log("Signup 组件即将被销毁")
  },
  mounted() {
    console.log("Signup 组件挂载完毕")
  },
  data() {
    return {
      msg: 'signup component',
      codeUrl: "",
      qId: "",
      token: "",
      po: po
    }
  },
  components: {},
  methods: {
    // 页面跳转
    goTo() {
      //直接跳转
      this.$router.push('/about');

      //带参数跳转
      //this.$router.push({path:'/testDemo',query:{setid:123456}});
      //this.$router.push({name:'testDemo',params:{setid:111222}});
    },
    // 注册方法
    signup() {
      var api = 'http://127.0.0.1:8888/signup';
      var p = {
        phone: this.$refs.phone.value,
        password: this.$refs.password.value,
        nickName: this.$refs.nickName.value,
        email: this.$refs.email.value,
        resoult: this.$refs.resoult.value,
        questionId: this.qId,
      };
      this.$http.post(api, p).then((response) => {
        if (response.data.code === "1") {
          console.log("res token:" + response.data.token)
          this.token = response.data.token
          console.log("res phone:" + response.data.phone)
          console.log("res code:" + response.data.code)

          // 调用方法切换组件
          this.goTo()
        } else {
          window.alert("请求错误:" + response.data.msg)
        }

        // 将后端返回的toke写入data.token
        this.token = response.data.token;
        //注意this指向
      }, function (err) {
        console.log("system error:" + err);
      })
    },
    // 获取验证图片
    getCapture() {
      // 获取图片流
      var api = 'http://127.0.0.1:8888/captchaImage';
      this.$http.get(api).then((response) => {
        // 将后端返回的图片流写入data.codeUrl
        this.codeUrl = response.data.img;
        // 将后端返回的问题qId写入data.qId
        this.qId = response.data.qId;
        console.log("qId:" + this.qId)
        //注意this指向
      }, function (err) {
        console.log(err);
      })
    },
    // 登陆方法
    login() {
      var api = 'http://127.0.0.1:8888/login';
      var p = {
        phone: this.$refs.loginPhone.value,
        password: this.$refs.loginPassword.value,
        note: this.$refs.loginNote.value,
      };
      this.$http.post(api, p).then((response) => {
        if (response.data.code === "1") {
          window.alert("res:" + response.data.token)
          this.token = response.data.token
          this.goTo()
        } else {
          window.alert("请求错误:" + response.data.msg);
        }
        //注意this指向
      }, function (err) {
        console.log(err);
      })
    },
    // 获取服务器验证码
    getNote() {
      //请求数据
      var api = 'http://127.0.0.1:8888/note';
      var p = {
        phone: this.$refs.loginPhone.value,
      };
      this.$http.post(api, p).then((response) => {
        if (response.data.code === "1") {
          window.alert("短信验证码是:" + response.data.note)
        } else {
          window.alert("错误请求:" + response.data.msg)
        }

        //注意this指向
      }, function (err) {
        console.log(err);
      })
    },
    cambiar_login() {
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
      document.querySelector('.cont_form_login').style.display = "block";
      document.querySelector('.cont_form_sign_up').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_login').style.opacity = "1";
      }, 400);

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
      }, 200);
    },
    cambiar_sign_up() {
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
      document.querySelector('.cont_form_sign_up').style.display = "block";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
      }, 100);

      setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
      }, 400);

      // 获取图片流
      this.getCapture()
    },
    ocultar_login_sign_up() {
      document.querySelector('.cont_forms').className = "cont_forms";
      document.querySelector('.cont_form_sign_up').style.opacity = "0";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
      }, 100);
    }
  }
}
</script>

<style scoped>

input {
  opacity: 0.5
}

body {
  width: 100%;
  height: 100%;
}

.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-size: cover;
  width: 100%;
  position: absolute;

  margin: 0;
  padding: 0;

  background-size: 100% 100%;
  -moz-background-size: 100% 100%;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  opacity: 0.8;
  border-radius: 6px;
  background: #ffffff;
  padding: 25px 25px 25px 25px;
}

.el-input {
  height: 38px;
}

.input-icon {
  height: 39px;
  width: 14px;
  margin-left: 2px;
}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  height: 38px;
  float: right;
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-size: 12px;
  letter-spacing: 1px;
}

.login-code-img {
  height: 38px;
}


* {
  margin: 0px auto;
  padding: 0px;
  text-align: center;
  font-family: 'Lato', sans-serif;
}

.cotn_principal {
  position: absolute;
  width: 100%;
  height: 100%;
  /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#cfd8dc+0,607d8b+100,b0bec5+100 */
  background: #aac4bc; /* Old browsers */
  background: -moz-linear-gradient(-45deg, #aac4bc 0%, #eca8a8 100%, #eed5a9 100%); /* FF3.6-15 */
  background: -webkit-linear-gradient(-45deg, #aac4bc 0%, #eca8a8 100%, #eed5a9 100%); /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(135deg, #aac4bc 0%, #eca8a8 100%, #eed5a9 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#cfd8dc', endColorstr='#b0bec5', GradientType=1); /* IE6-9 fallback on horizontal gradient */

}


.cont_centrar {
  position: relative;
  float: left;
  width: 100%;

}

.cont_login {
  position: relative;
  width: 640px;
  left: 50%;
  margin-left: -320px;

}

.cont_back_info {
  position: relative;
  float: left;
  width: 640px;
  height: 280px;
  overflow: hidden;
  background-color: #fff;
  margin-top: 100px;
  box-shadow: 1px 10px 30px -10px rgba(0, 0, 0, 0.5);
}

.cont_forms {
  position: absolute;
  overflow: hidden;
  top: 100px;
  left: 0px;
  width: 320px;
  height: 280px;
  background-color: #eee;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}

.cont_forms_active_login {
  box-shadow: 1px 10px 30px -10px rgba(0, 0, 0, 0.5);
  height: 580px;
  top: 20px;
  left: 0px;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;

}

.cont_forms_active_sign_up {
  box-shadow: 1px 10px 30px -10px rgba(0, 0, 0, 0.5);
  height: 580px;
  top: 20px;
  left: 320px;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}

.cont_img_back_grey {
  position: absolute;
  width: 950px;
  top: -80px;
  left: -116px;
}

.cont_img_back_grey > img {
  width: 100%;
  -webkit-filter: grayscale(100%);
  filter: grayscale(100%);
  opacity: 0.2;
  animation-name: animar_fondo;
  animation-duration: 20s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
  animation-direction: alternate;

}

.cont_img_back_ {
  position: absolute;
  width: 950px;
  top: -80px;
  left: -116px;
}

.cont_img_back_ > img {
  width: 100%;
  opacity: 0.3;
  animation-name: animar_fondo;
  animation-duration: 20s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}

.cont_forms_active_login > .cont_img_back_ {
  top: 0px;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}

.cont_forms_active_sign_up > .cont_img_back_ {
  top: 0px;
  left: -435px;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}


.cont_info_log_sign_up {
  position: absolute;
  width: 640px;
  height: 280px;
  top: 100px;
  z-index: 1;
}

.col_md_login {
  position: relative;
  float: left;
  width: 50%;
}

.col_md_login > h2 {
  font-weight: 400;
  margin-top: 70px;
  color: #757575;
}

.col_md_login > p {
  font-weight: 400;
  margin-top: 15px;
  width: 80%;
  color: #37474F;
}

.btn_login {
  background-color: #FF9800;
  border: none;
  padding: 10px;
  width: 200px;
  border-radius: 3px;
  box-shadow: 1px 5px 20px -5px rgba(0, 0, 0, 0.4);
  color: #fff;
  margin-top: 10px;
  cursor: pointer;
}

.btn_login2 {
  background-color: #FF9800;
  border: none;
  padding: 10px;
  width: 200px;
  border-radius: 3px;
  box-shadow: 1px 5px 20px -5px rgba(0, 0, 0, 0.4);
  color: #fff;
  margin-top: 35px;
  cursor: pointer;
}

.note_login {
  background-color: forestgreen;
  border: none;
  padding: 10px;
  width: 180px;
  border-radius: 3px;
  box-shadow: 1px 5px 20px -5px rgba(0, 0, 0, 0.4);
  color: #fff;
  margin-top: 35px;
  cursor: pointer;
}

.col_md_sign_up {
  position: relative;
  float: left;
  width: 50%;
}

.cont_ba_opcitiy > h2 {
  font-weight: 400;
  color: #fff;
}

.cont_ba_opcitiy > p {
  font-weight: 400;
  margin-top: 15px;
  color: #fff;
}

/* ----------------------------------
background text
------------------------------------
 */
.cont_ba_opcitiy {
  position: relative;
  background-color: rgba(187, 168, 170, 0.79);
  width: 80%;
  border-radius: 3px;
  margin-top: 60px;
  padding: 15px 0px;
}

.btn_sign_up {
  background-color: #f44336;
  border: none;
  padding: 10px;
  width: 200px;
  border-radius: 3px;
  box-shadow: 1px 5px 20px -5px rgba(0, 0, 0, 0.4);
  color: #fff;
  margin-top: 10px;
  cursor: pointer;
}

.cont_forms_active_sign_up {
  z-index: 2;
}


@-webkit-keyframes animar_fondo {
  from {
    -webkit-transform: scale(1) translate(0px);
    -moz-transform: scale(1) translate(0px);
    -ms-transform: scale(1) translate(0px);
    -o-transform: scale(1) translate(0px);
    transform: scale(1) translate(0px);
  }
  to {
    -webkit-transform: scale(1.5) translate(50px);
    -moz-transform: scale(1.5) translate(50px);
    -ms-transform: scale(1.5) translate(50px);
    -o-transform: scale(1.5) translate(50px);
    transform: scale(1.5) translate(50px);
  }
}

@-o-keyframes identifier {
  from {
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
    transform: scale(1);
  }
  to {
    -webkit-transform: scale(1.5);
    -moz-transform: scale(1.5);
    -ms-transform: scale(1.5);
    -o-transform: scale(1.5);
    transform: scale(1.5);
  }

}

@-moz-keyframes identifier {
  from {
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
    transform: scale(1);
  }
  to {
    -webkit-transform: scale(1.5);
    -moz-transform: scale(1.5);
    -ms-transform: scale(1.5);
    -o-transform: scale(1.5);
    transform: scale(1.5);
  }

}

@keyframes identifier {
  from {
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
    transform: scale(1);
  }
  to {
    -webkit-transform: scale(1.5);
    -moz-transform: scale(1.5);
    -ms-transform: scale(1.5);
    -o-transform: scale(1.5);
    transform: scale(1.5);
  }
}

.cont_form_login {
  position: absolute;
  opacity: 0;
  display: none;
  width: 320px;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}

.cont_forms_active_login {
  z-index: 2;
}

.cont_forms_active_login > .cont_form_login {
}

.cont_form_sign_up {
  position: absolute;
  width: 320px;
  float: left;
  opacity: 0;
  display: none;
  -webkit-transition: all 0.5s;
  -moz-transition: all 0.5s;
  -ms-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}


.cont_form_sign_up > input {
  text-align: left;
  padding: 15px 5px;
  margin-left: 10px;
  margin-top: 20px;
  width: 260px;
  border: none;
  color: #757575;
}

.cont_form_sign_up > h2 {
  margin-top: 50px;
  font-weight: 400;
  color: #757575;
}


.cont_form_login > input {
  padding: 15px 5px;
  margin-left: 10px;
  margin-top: 35px;
  width: 260px;
  border: none;
  text-align: left;
  color: #757575;
}

.cont_form_login > h2 {
  margin-top: 110px;
  font-weight: 400;
  color: #757575;
}

.cont_form_login > a, .cont_form_sign_up > a {
  color: #757575;
  position: relative;
  float: left;
  margin: 10px;
  margin-left: 30px;
}


.localimg {
  cursor: pointer;
  border: 0;
  display: inline;
  vertical-align: middle;
  padding-top: 20px;
  padding-bottom: 10px;
}

.right {
  color: green;
}

.error {
  color: red;
}

p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}

.tips {
  color: #f66495;
  float: left;
}


@font-face {
  font-family: 'Material Icons';
  font-style: normal;
  font-weight: 400;
  src: url(https://fonts.gstatic.com/s/materialicons/v94/flUhRq6tzZclQEJ-Vdg-IuiaDsNcIhQ8tQ.woff2) format('woff2');
}

.material-icons {
  font-family: 'Material Icons';
  font-weight: normal;
  font-style: normal;
  font-size: 24px;
  line-height: 1;
  letter-spacing: normal;
  text-transform: none;
  display: inline-block;
  white-space: nowrap;
  word-wrap: normal;
  direction: ltr;
  -webkit-font-feature-settings: 'liga';
  -webkit-font-smoothing: antialiased;
}

</style>

