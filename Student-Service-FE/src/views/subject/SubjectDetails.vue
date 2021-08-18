<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px; margin-bottom: 50px;">
        <h3>Subject Details</h3>

        <MyInputComponent name="id" label="Subject Id" v-model="subject.id" readonly></MyInputComponent>

        <MyInputComponent name="name" label="Name" v-model="subject.name" readonly></MyInputComponent>

        <MyInputComponent name="description" label="Description" v-model="subject.description" readonly></MyInputComponent>

        <MyInputComponent name="noOfESP" label="No. of ESP" v-model="subject.noOfESP" readonly></MyInputComponent>

        <MyInputComponent name="yearOfStudy" label="Year of study" v-model="subject.yearOfStudy" readonly></MyInputComponent>

        <MyInputComponent name="semester" label="Semester" v-model="subject.semester" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">Professor</label>
          <input type="text" class="form-control" :value="professor.firstName + ' ' + professor.lastName" readonly/>
        </div>

        <button class="btn btn-outline-primary" @click="returnBack">Back to subjects</button>
        
      </div>
    </div>
  </div>

</template>

<script>
import MyInputComponent from "@/components/input/MyInputControl.vue";
import SubjectService from "@/services/SubjectService.js";

export default {
  components: { MyInputComponent },
  props: {
    subjectId: {
      type: String,
      required: true,
    },
  },
  created() {
    if (this.subjectId) {
      SubjectService.getSubject(this.subjectId).then((response) => {
        this.subject = response.data;
        this.professor.id = this.subject.professor.id;
        this.professor.firstName = this.subject.professor.firstName;
        this.professor.lastName = this.subject.professor.lastName;
      });
    }
    this.getAllProfessors();
  },
  data() {
    return {
      professorList: [],
      subject: {},
      professor: {
        id: null,
        firstName: "",
        lastName: ""
      },
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
    getAllProfessors() {
      SubjectService.getAllProfessors().then((response) => {
        console.log(response);
        this.professorList = response.data;
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
