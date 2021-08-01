<template>
  <div>
    <v-header></v-header>
    <h2>这是一个登陆注册组件</h2>
    <div class="cotn_principal">
      <div class="cont_centrar">
        <div class="cont_login">
          <div class="cont_info_log_sign_up">
            <div class="col_md_login">
              <div class="cont_ba_opcitiy">
                <h2>LOGIN</h2>
                <p>Lorem ipsum dolor sit amet, consectetur.</p>
                <button class="btn_login" v-on:click="cambiar_login">LOGIN</button>
              </div>
            </div>
            <div class="col_md_sign_up">
              <div class="cont_ba_opcitiy">
                <h2>SIGN UP</h2>
                <p>Lorem ipsum dolor sit amet, consectetur.</p>
                <button class="btn_sign_up" v-on:click="cambiar_sign_up">SIGN UP</button>
              </div>
            </div>
          </div>
          <div class="cont_back_info">
            <div class="cont_img_back_grey"><img src="/img/po.jpg" alt=""/></div>
          </div>
          <div class="cont_forms">
            <div class="cont_img_back_"><img src="/img/po.jpg" alt=""/></div>
            <div class="cont_form_login"><a href="#" v-on:click="ocultar_login_sign_up"><i
              class="material-icons">&#xE5C4;</i></a>
              <h2>LOGIN</h2>

              <input id="loginPhone" type="text" placeholder="手机号" onblur="checkPhone"/>
              <h5 id="loginPhoneText"></h5>

              <input id="loginPassword" type="password" placeholder="密码" onblur="checkPassword()"/>
              <h5 id="loginPasswordText"></h5>


              <input id=loginNote type="password" placeholder="短信验证码" onblur="checkNote()"/>
              <h5 id=loginNoteText></h5>


              <button id="noteButton" class="note_login">点击获取短信验证码</button>

              <button id="loginButton" class="btn_login2">LOGIN</button>
            </div>
            <div class="cont_form_sign_up"><a href="#" v-on:Click="ocultar_login_sign_up"><i class="material-icons">&#xE5C4;</i></a>
              <h2>SIGN UP</h2>
              <input id="phone" type="text" placeholder="phone"/>
              <input id="password" type="password" placeholder="password"/>

              <input id="nickName" type="text" placeholder="nickName"/>
              <input id="email" type="text" placeholder="email"/>

              <input id="captcha" type="text" class="yzm" placeholder="click down pitcher to refresh"/>

              <input id="questionId" type="hidden" class="hide-icon" placeholder="questionId"/>
              <!--<input id="questionId" type="text" class="input_text"/>-->
              <img src="/img/po.jpg" id="imgCode" class="localimg" style="cursor:pointer;border:0; display:inline;vertical-align:middle;"/>

              <button id="signup" class="btn_sign_up">SIGN UP</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import Header from './Header.vue';

export default {
  data() {
    return {
      msg: 'signup component'
    }
  },
  components: {
    'v-header': Header
  },
  methods:{
    cambiar_login(){
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
    cambiar_sign_up(){
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
      document.querySelector('.cont_form_sign_up').style.display = "block";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
      }, 100);

      setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
      }, 400);
    },
    ocultar_login_sign_up(){
      document.querySelector('.cont_forms').className = "cont_forms";
      document.querySelector('.cont_form_sign_up').style.opacity = "0";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
      }, 500);
    },


  }
}
</script>

<style scoped>

</style>

