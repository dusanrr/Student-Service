<template>
  <div class="form-container m-4 col-6 col-md-8 col-sm-10">
    <h3 v-if="actionType === 'new'">New Student</h3>
    <h3 v-else>Edit Student</h3>
    <MyInputComponent
      name="indexNumber"
      label="Index number"
      v-model="indexNumber"
      :vcomp="v$.indexNumber"
    >
    </MyInputComponent>

    <MyInputComponent
      name="indexYear"
      label="Index year"
      v-model="indexYear"
      :vcomp="v$.indexYear"
    >
    </MyInputComponent>

    <MyInputComponent
      name="firstName"
      label="First name"
      v-model="firstName"
      :vcomp="v$.firstName"
    >
    </MyInputComponent>

    <MyInputComponent
      name="lastName"
      label="Last name"
      v-model="lastName"
      :vcomp="v$.lastName"
    >
    </MyInputComponent>

    <MyInputComponent
      name="email"
      label="Email"
      v-model="email"
      :vcomp="v$.email"
    >
    </MyInputComponent>

    <MyInputComponent
      name="adress"
      label="Adress"
      v-model="adress"
      :vcomp="v$.adress"
    >
    </MyInputComponent>

    <div>
      <select class="form-control" v-model="cityDto">
        <option v-for="city in cityList" :value="city" :key="city.number">
          {{ city.name }}
        </option>
      </select>
    </div>

    <MyInputComponent
      name="currentYearOfStudy"
      label="Current year of study"
      v-model="currentYearOfStudy"
      :vcomp="v$.currentYearOfStudy"
    >
    </MyInputComponent>

    <div class="d-flex flex-row-reverse">
      <button class="btn btn-outline-primary" @click="saveStudent">Save</button>
    </div>
  </div>
</template>

<script>
import useValidate from "@vuelidate/core";
import {
  required,
  //maxValue,
  minLength,
  maxLength,
  email,
} from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import StudentService from "@/services/StudentService.js";
import { addMessage } from "@/main.js";

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
    if (this.studentId) {
      StudentService.getStudent(this.studentId).then((response) => {
        const student = response.data;
        this.indexNumber = student.indexNumber;
        this.indexYear = student.indexYear;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.adress = student.adress;
        this.cityDto.number = student.cityDto.number;
        this.cityDto.name = student.cityDto.name;
        this.currentYearOfStudy = student.currentYearOfStudy;
      });
    }
    this.getAllCities();
  },
  data() {
    return {
      v$: useValidate(),
      cityList: [],
      indexNumber: "",
      indexYear: "",
      firstName: "",
      lastName: "",
      email: "",
      adress: "",
      cityDto: {
        number: null,
        name: "",
      },
      currentYearOfStudy: null,
    };
  },
  validations() {
    return {
      indexNumber: {
        required,
        minLength: minLength(4),
        maxLength: maxLength(4),
      },
      indexYear: {
        required,
        //minLength: minLength(4),
        maxLength: maxLength(4),
      },
      firstName: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(30),
      },
      lastName: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(30),
      },
      email: {
        required,
        email
      },
      adress: {
        minLength: minLength(3),
        maxLength: maxLength(50),
      },
      cityDto: {
        required
      },
      currentYearOfStudy: {
        required,
        //minLength: minLength(1),
        maxLength: maxLength(7),
      },
    };
  },
  methods: {
    saveStudent() {
      if (this.actionType && this.actionType === "new") {
        this.insertStudent();
      } else {
        this.updateStudent();
      }
    },
    insertStudent() {
      StudentService.insertStudent({
        indexNumber: this.indexNumber,
        indexYear: this.indexYear,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        adress: this.adress,
        cityDto: {
          number: this.cityDto.number,
          name: this.cityDto.name,
        },
        currentYearOfStudy: this.currentYearOfStudy,
      })
        .then((response) => {
          console.log("Student inserted" + response);
          addMessage({
            message: "Student inserted",
            type: "success",
            title: "STUDENT",
          });
          this.$router.push("/student-list");
        })
        .catch((error) => {
          addMessage({
            message: "Insert was not successful:" + error,
            type: "danger",
            title: "Insert student",
          });
          this.$router.push("/student-list");
        });
    },
    updateStudent() {
      StudentService.editStudent({
        indexNumber: this.indexNumber,
        indexYear: this.indexYear,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        adress: this.adress,
        cityDto: {
          number: this.cityDto.number,
          name: this.cityDto.name,
        },
        currentYearOfStudy: this.currentYearOfStudy,
      })
        .then((response) => {
          console.log("Student updated" + response);
          addMessage({
            message: "Student updated",
            type: "success",
            title: "STUDENT",
          });
          this.$router.push("/student-list");
        })
        .catch((error) => {
          addMessage({
            message: "Update was not successful:" + error,
            type: "danger",
            title: "Update student",
          });
          this.$router.push("/student-list");
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
