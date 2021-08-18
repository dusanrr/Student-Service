<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px; margin-bottom: 50px;">
        <h3>Student Details</h3>

        <MyInputComponent name="indexNumber" label="Index number" v-model="student.indexNumber" readonly></MyInputComponent>

        <MyInputComponent name="indexYear" label="Index year" v-model="student.indexYear" readonly></MyInputComponent>

        <MyInputComponent name="firstName" label="First name" v-model="student.firstName" readonly></MyInputComponent>

        <MyInputComponent name="lastName" label="Last name" v-model="student.lastName" readonly></MyInputComponent>

        <MyInputComponent name="email" label="Email" v-model="student.email" readonly></MyInputComponent>

        <MyInputComponent name="adress" label="Adress" v-model="student.adress" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">City</label>
          <select class="form-control" v-model="student.city" readonly>
            <option v-for="city in cityList" :value="city" :key="city.number">
              {{ city.name }}
            </option>
          </select>
        </div>

        <MyInputComponent name="currentYearOfStudy" label="Current year of study" v-model="student.currentYearOfStudy" readonly></MyInputComponent>

        <button class="btn btn-outline-primary" @click="returnBack">Back to students</button>

      </div>
    </div>
  </div>

</template>

<script>

import MyInputComponent from "@/components/input/MyInputControl.vue";
import StudentService from "@/services/StudentService.js";

export default {
  name: 'StudentDetails',
  components: { MyInputComponent },
  props: {
    studentId: {
      type: String,
      required: true,
    }
  },
  created() {
    if (this.studentId) {
      StudentService.getStudent(this.studentId).then((response) => {
        this.student = response.data;
      });
    }
    this.getAllCities();
  },
  updated(){
    this.vueLoader();
  },
  data() {
    return {
      cityList: [],
      student:[],
    };
  },
  methods: {
    vueLoader()
      {
        let loader = this.$loading.show({
        // Optional parameters
        container: this.$refs.loadingContainer,
        color: '#0C937A',
        loader: 'dots',
        width: 64,
        height: 64,
        backgroundColor: '#EFEBEB',
        opacity: 1.0,
        zIndex: 999,
        });
        // simulate AJAX
        setTimeout(() => {
            loader.hide()
        }, 3000)
    },
    returnBack() {
      this.$router.go(-1);
    },
    getAllCities() {
      StudentService.getAllCities().then((response) => {
        console.log(response);
        this.cityList = response.data;
      });
    },
  },
};
</script>

<style scoped>
.label-left
{
  text-align: left;
  padding-bottom: 10px;
}

img{
  height: 200px;
  width: 200px;
  margin-top: 30px;
}
</style>

