<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4 mb-3">
        <label for="name"> Name</label>
        <input type="text" class="form-control" id="name" v-model="user.name" required>
      </div>
      <div class="col-md-4 mb-3">
        <label for="nickname">Nickname</label>
        <input type="text" class="form-control" id="nickname" v-model="user.nickName" value="Otto" required>
      </div>
      <div class="col-md-4 mb-3">
        <label for="role">Role</label>
        <input type="text" class="form-control" id="role" v-model="role" readonly required>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="phone">Phone</label>
        <input type="text" class="form-control" id="phone" v-model="user.phone" required>
      </div>
      <div class="col-md-6 mb-3">
        <label for="email">Email</label>
        <input type="text" class="form-control" id="email" v-model="user.email" required>
      </div>
    </div>

    <div class="-group">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
        <label class="form-check-label" for="invalidCheck2">
          Agree to terms and conditions
        </label>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 mb-3">
        <button class="btn btn-primary" type="submit">Submit form</button>
      </div>
      <div class="col-md-6 mb-3">
        <FileUpload/>
      </div>
    </div>

    <div class="col-md-6 mb-3">
    </div>
  </div>
</template>

<script>

import axios from "axios";
import FileUpload from "./FileUpload";

export default {
  data() {
    return {
      user: "",
      permissionArray: [],
      hasPermission: '',
      path: "user:detail",
      role: ""
    }
  },
  components: {FileUpload},

  mounted() {
    var data = {'token': localStorage.getItem('token'), uid: localStorage.getItem('uid')}
    axios
      .post("/getUserDetail", data)
      .then(response => {
        this.user = response.data.user;
        var roleId = this.user.roleId;
        this.role = roleId === 1 ? '超级管理员' : roleId === 2 ? "管理员" : "普通用户";

        // 判断该用户的权限路径里是否包含该页面的访问路径"user:detail"
        this.hasPermission = (this.user.permissionArray.indexOf(this.path) >= 0);
      })
      .catch(err => {
        console.log("调用出错:" + err);
      });
  },
}
</script>

<style scoped>

input {
  opacity: 0.8;
}

.container {
  max-width: 650px;
}

label {
  float: left;
}
</style>


