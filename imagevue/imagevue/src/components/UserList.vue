<template>
  <div class="container">

    <!--添加模态窗口显示-->
    <div>
      <b-modal ref="my-modal" size="lg" hide-footer title="Edit user info">
        <div class="d-block text-center">
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
        </div>
        <b-button class="mt-2" variant="outline-success" block @click="hideModal">Save</b-button>
        <b-button class="mt-2" variant="outline-danger" block @click="toggleModal">Cancle</b-button>
      </b-modal>
    </div>

    <table class="table table-hover table-bordered">
      <thead>
      <tr>
        <th>
          <input type="checkbox" v-model='checked' @click='checkedAll()'>
        </th>
        <th>name</th>
        <th>nickName</th>
        <th>role</th>
        <th>option</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(user,index) in list" :key="index">
        <td>
          <input type="checkbox" v-model='checkList' :value="user.uid">
        </td>
        <td>{{ user.name }}</td>
        <td>{{ user.nickName }}</td>
        <td>{{ user.role }}</td>

        <td>
          <b-btn @click="modifyData(user.uid)" class="bg-success">修改</b-btn>
          ｜
          <b-btn @click='deleteData(user.uid)' class="btn-danger">删除</b-btn>
        </td>
      </tr>

      </tbody>
    </table>
    <!--    <b-table hover :items="list" :fields="fields"></b-table>-->

  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'UserList',
  data () {
    return {
      userList: [],
      list: [],
      fields: ['name', 'nickName', 'role'],

      user: '',
      permissionArray: [],
      hasPermission: '',
      path: 'user:detail',
      role: '',

      checked: false,
      allProductTotal: null,
      checkList: []

    }
  },
  components: {},
  beforeDestroy () {
    console.log('UserList 组件即将被销毁')
  },
  mounted () {
    console.log('UserList 组件挂载完毕')
    var data = {'token': localStorage.getItem('token'), 'phone': 136}
    axios
      .post('/getUserList', data)
      .then(response => {
        this.userList = response.data.userList
        this.list = response.data.list
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    // 编辑单挑信息
    modifyData (uid) {
      const data = {'token': localStorage.getItem('token'), uid: uid};
      axios
        .post('/getUserDetail', data)
        .then(response => {
          this.user = response.data.user
          var roleId = this.user.roleId
          this.role = roleId === 1 ? '超级管理员' : roleId === 2 ? '管理员' : '普通用户'

          // 判断该用户的权限路径里是否包含该页面的访问路径"user:detail"
          this.hasPermission = (this.user.permissionArray.indexOf(this.path) >= 0)
        })
        .catch(err => {
          console.log('调用出错:' + err)
        })

      this.showModal()
    },

    // https://www.cnblogs.com/yingzi1028/p/7781829.html(代码参考来源)
    checkedAll: function () {
      var _this = this
      console.log(_this.checkList)
      if (_this.checked) {
        // 实现反选
        _this.checkList = []
      } else {
        // 实现全选
        _this.checkList = []
        _this.userList.forEach(function (item, index) {
          if (index >= 0) {
            _this.checkList.push(item.uid)
          }
        })
      }
    },
    deleteData (uid) {
      var data = {'token': localStorage.getItem('token'), uid: uid}
      axios
        .post('/deleteUser', data)
        .then(response => {
          window.alert('msg:' + response.data.msg)
          this.$router.go(0)
        })
        .catch(err => {
          console.log('调用出错:' + err)
        })
    },
    showModal () {
      this.$refs['my-modal'].show()
    },
    hideModal () {
      this.$refs['my-modal'].hide()
    },
    toggleModal () {
      // We pass the ID of the button that we want to return focus to
      // when the modal has hidden
      this.$refs['my-modal'].toggle('#toggle-btn')
    }
  },
  // https://www.cnblogs.com/yingzi1028/p/7781829.html(代码参考来源)
  watch: {
    // 深度 watcher
    'checkList': {
      handler: function (val, oldVal) {
        this.checked = val.length === this.list.length
      },
      deep: true
    }
  }
}
</script>
<style scoped>

* {
  margin: 0 auto;
  padding: 0;
  text-align: center;
  font-family: 'Lato', sans-serif;
}

</style>
