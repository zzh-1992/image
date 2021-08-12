<template>
  <div class="row">
    <div class="col-lg-3 col-6">
      <!-- small box -->
      <div class="small-box bg-info">
        <div class="inner">
          <h3>150</h3>

          <p>New Orders</p>
        </div>
        <div class="icon">
          <i class="ion ion-bag"></i>
        </div>
        <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
      </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-3 col-6">
      <!-- small box -->
      <div class="small-box bg-success">
        <div class="inner">
          <h3>53<sup style="font-size: 20px">%</sup></h3>

          <p>Bounce Rate</p>
        </div>
        <div class="icon">
          <i class="ion ion-stats-bars"></i>
        </div>
        <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
      </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-3 col-6">
      <!-- small box -->
      <div class="small-box bg-warning">
        <div class="inner">
          <h3>44</h3>

          <p>User Registrations</p>
        </div>
        <div class="icon">
          <i class="ion ion-person-add"></i>
        </div>
        <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
      </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-3 col-6">
      <!-- small box -->
      <div class="small-box bg-danger">
        <div class="inner">
          <h3>65</h3>

          <p>Unique Visitors</p>
        </div>
        <div class="icon">
          <i class="ion ion-pie-graph"></i>
        </div>
        <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
      </div>
    </div>

    <div v-for="(item, index) in technicalList" :key="item.id" class="col-lg-3 col-6">
      <div class="inner">
        <h3>{{ item.id }}</h3>
        <h3>{{ item.title }}</h3>
        <h3>{{ item.officialLink }}</h3>
      </div>
      <div class="icon">
        <i class="ion ion-pie-graph"></i>
      </div>
      <a :href="'' + item.officialLink" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
    </div>

  </div>


</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      technicalList: [],
    }
  },
  mounted() {
    var data = {'token': localStorage.getItem('token'), 'phone': 136}
    axios
      .post("http://127.0.0.1:8888/getTechnicals", data)
      .then(response => {
        this.technicalList = response.data.technicalList;
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {}
}
</script>
<style scoped>

</style>
