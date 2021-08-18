<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px; margin-bottom: 50px;">
        <h3>Professor Details</h3>

        <MyInputComponent name="id" label="Professor Id" v-model="professor.id" readonly></MyInputComponent>

        <MyInputComponent name="firstName" label="First name" v-model="professor.firstName" readonly> </MyInputComponent>

        <MyInputComponent name="lastName" label="Last name" v-model="professor.lastName" readonly></MyInputComponent>

        <MyInputComponent name="email" label="Email" v-model="professor.email" readonly></MyInputComponent>

        <MyInputComponent name="adress" label="Adress" v-model="professor.adress" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">City</label>
          <div>
            <select class="form-control" v-model="professor.city" readonly>
              <option v-for="city in cityList" :value="city" :key="city.number">
                {{ city.name }}
              </option>
            </select>
          </div>
        </div>

        <MyInputComponent name="phone" label="Phone" v-model="professor.phone" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">Title</label>
          <select class="form-control" v-model="professor.title" readonly>
            <option v-for="title in titleList" :value="title" :key="title.id">
              {{ title.titleName }}
            </option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label">Reelection date</label>
          <input type="date" v-model="professor.reelectionDate" class="form-control" readonly/>
        </div>

         <button class="btn btn-outline-primary" @click="returnBack">Back to professors</button>

      </div>
    </div>
  </div>

</template>

<script>
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ProfessorService from "@/services/ProfessorService.js";

export default {
  components: { MyInputComponent },
  props: {
    professorId: {
      type: String,
      required: true,
    },
  },
  created() {
    if (this.professorId) {
      ProfessorService.getProfessor(this.professorId).then((response) => {
        this.professor = response.data;
      });
    }
    this.getAllCities();
    this.getAllTitles();
  },
  data() {
    return {
      cityList: [],
      titleList: [],
      professor: {}
    };
  },
  updated(){
    this.vueLoader();
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
      ProfessorService.getAllCities().then((response) => {
        console.log(response);
        this.cityList = response.data;
      });
    },
    getAllTitles() {
      ProfessorService.getAllTitles().then((response) => {
        console.log(response);
        this.titleList = response.data;
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