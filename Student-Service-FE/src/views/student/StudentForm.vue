<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px;">
        <h3 v-if="actionType === 'new'">New Student</h3>
        <h3 v-else>Edit Student</h3>

        <MyInputComponent requiredField="true" name="indexNumber" label="Index number" v-model="student.indexNumber" :vcomp="v$.student.indexNumber"></MyInputComponent>

        <MyInputComponent requiredField="true" name="indexYear" label="Index year" v-model="student.indexYear" :vcomp="v$.student.indexYear"></MyInputComponent>

        <MyInputComponent requiredField="true" name="firstName" label="First name" v-model="student.firstName" :vcomp="v$.student.firstName"></MyInputComponent>

        <MyInputComponent requiredField="true" name="lastName" label="Last name" v-model="student.lastName" :vcomp="v$.student.lastName"></MyInputComponent>

        <MyInputComponent name="email" label="Email" v-model="student.email" :vcomp="v$.student.email"></MyInputComponent>

        <MyInputComponent name="adress" label="Adress" v-model="student.adress" :vcomp="v$.student.adress"></MyInputComponent>

        <div class="label-left">
          <label class="form-label">City</label>
          <select class="form-control" v-model="student.city">
            <option v-for="city in cityList" :value="city" :key="city.number">
              {{ city.name }}
            </option>
          </select>
        </div>

        <MyInputComponent requiredField="true" name="currentYearOfStudy" label="Current year of study" v-model="student.currentYearOfStudy" :vcomp="v$.student.currentYearOfStudy"></MyInputComponent>

        <button class="btn btn-outline-primary" @click="saveStudent" :disabled='!isComplete'>Save</button>
        <button class="btn btn-outline-danger cancel" @click="returnBack">Cancel</button>

      </div>
    </div>
  </div>

</template>

<script>
import useValidate from "@vuelidate/core";
import { required, minLength, maxLength, email } from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import StudentService from "@/services/StudentService.js";

export default {
  components: { MyInputComponent },
  props: {
    studentId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    this.vueLoader();
    if (this.studentId) {
      StudentService.getStudent(this.studentId).then((response) => {
        this.student = response.data;
      });
    }
    this.getAllCities();
  },
  data() {
    return {
      v$: useValidate(),
      cityList: [],
      student: {},
    };
  },
  validations() {
    return {
      student:{
        indexNumber: { required, minLength: minLength(4), maxLength: maxLength(4) },
        indexYear: { required, maxLength: maxLength(4) },
        firstName: { required, minLength: minLength(3), maxLength: maxLength(30) },
        lastName: { required, minLength: minLength(3), maxLength: maxLength(30) },
        email: { email },
        adress: { minLength: minLength(3), maxLength: maxLength(50) },
        city: { },
        currentYearOfStudy: { required, maxLength: maxLength(7) }
      },
    };
  },
  computed: {
    isComplete () {
      return this.student.indexNumber && this.student.indexYear && this.student.firstName && this.student.lastName && this.student.currentYearOfStudy;
    }
  },
  methods: {
    returnBack() {
      this.$router.go(-1);
    },
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
    saveStudent() {
      if (this.actionType && this.actionType === "new") {
        this.insertStudent();
      } else {
        this.updateStudent();
      }
    },
    insertStudent() {
      StudentService.insertStudent(this.student)
        .then((response) => {
          console.log("Student inserted" + response.data);

          this.$toast.show("Student inserted!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/student-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateStudent() {
      StudentService.editStudent(this.student)
        .then((response) => {
          console.log("Student updated" + response.data);
          
          this.$toast.show("Student updated!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/student-list");
        })
        .catch((error) => {
          console.log(error);
        });
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

.cancel{
  margin-left: 10px;
}
</style>

