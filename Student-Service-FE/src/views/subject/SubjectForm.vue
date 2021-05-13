<template>
  <div class="form-container m-4 col-6 col-md-8 col-sm-10">
    <h3 v-if="actionType === 'new'">New Subject</h3>
    <h3 v-else>Edit Subject</h3>

    <MyInputComponent
      readonly
      name="id"
      label="Subject Id"
      v-model="id"
      :vcomp="v$.id"
      v-if="actionType !== 'new'"
    >
    </MyInputComponent>

    <MyInputComponent 
      name="name" 
      label="Name" 
      v-model="name" 
      :vcomp="v$.name">
    </MyInputComponent>

    <MyInputComponent
      name="description"
      label="Description"
      v-model="description"
      :vcomp="v$.description"
    >
    </MyInputComponent>

    <MyInputComponent
      name="noOfESP"
      label="No. of ESP"
      v-model="noOfESP"
      :vcomp="v$.noOfESP"
    >
    </MyInputComponent>

    <MyInputComponent
      name="yearOfStudy"
      label="Year of study"
      v-model="yearOfStudy"
      :vcomp="v$.yearOfStudy"
    >
    </MyInputComponent>

    <label class="form-label">Semester</label>
    <div>
      <select class="form-control" v-model="semester">
        <option value="Summer">Summer</option>
        <option value="Winter">Winter</option>
      </select>
    </div>

    <label class="form-label">Professor</label>
    <div>
      <select class="form-control" v-model="professorDto">
        <option v-for="professor in professorList" :value="professor" :key="professor.id">
          {{ professor.firstName }} {{ professor.lastName }}
        </option>
      </select>
    </div>

    <div class="d-flex flex-row-reverse">
      <button class="btn btn-outline-primary" @click="saveSubject">Save</button>
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
} from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import SubjectService from "@/services/SubjectService.js";
import { addMessage } from "@/main.js";

export default {
  components: { MyInputComponent },
  props: {
    subjectId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    if (this.subjectId) {
      SubjectService.getSubject(this.subjectId).then((response) => {
        const subject = response.data;
        this.id = subject.id;
        this.name = subject.name;
        this.description = subject.description;
        this.noOfESP = subject.noOfESP;
        this.yearOfStudy = subject.yearOfStudy;
        this.semester = subject.semester;
        this.professorDto.id = subject.professorDto.id;
        this.professorDto.firstName = subject.professorDto.firstName;
        this.professorDto.lastName = subject.professorDto.lastName;
      });
    }
    this.getAllProfessors();
  },
  data() {
    return {
      v$: useValidate(),
      professorList: [],
      id: null,
      name: "",
      description: "",
      noOfESP: null,
      yearOfStudy: null,
      semester: "",
      professorDto: {
        id: null,
        firstName: "",
        lastName: ""
      },
    };
  },
  validations() {
    return {
      id: {
        required,
      },
      name: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(30),
      },
      description: {
        required,
        minLength: minLength(1),
        maxLength: maxLength(30),
      },
      noOfESP: {
        minLength: minLength(1),
        maxLength: maxLength(30),
      },
      yearOfStudy: {
        //minLength: minLength(1),
        maxLength: maxLength(1),
      },
      semester: {
        //minLength: minLength(1),
        maxLength: maxLength(10),
      },
    };
  },
  methods: {
    saveSubject() {
      if (this.actionType && this.actionType === "new") {
        this.insertSubject();
      } else {
        this.updateSubject();
      }
    },
    insertSubject() {
      SubjectService.insertSubject({
        name: this.name,
        description: this.description,
        noOfESP: this.noOfESP,
        yearOfStudy: this.yearOfStudy,
        semester: this.semester,
        professorDto: {
          id: this.professorDto.id,
          firstName: this.professorDto.firstName,
          lastName: this.professorDto.lastName,
        },
      })
        .then((response) => {
          console.log("Subject inserted" + response);
          addMessage({
            message: "Subject inserted",
            type: "success",
            title: "SUBJECT",
          });
          this.$router.push("/subject-list");
        })
        .catch((error) => {
          addMessage({
            message: "Insert was not successful:" + error,
            type: "danger",
            title: "Insert subject",
          });
          this.$router.push("/subject-list");
        });
    },
    updateSubject() {
      SubjectService.editSubject({
        id: this.id,
        name: this.name,
        description: this.description,
        noOfESP: this.noOfESP,
        yearOfStudy: this.yearOfStudy,
        semester: this.semester,
        professorDto: {
          id: this.professorDto.id,
          firstName: this.professorDto.firstName,
          lastName: this.professorDto.lastName,
        },
      })
        .then((response) => {
          console.log("Subject updated" + response);
          addMessage({
            message: "Subject updated",
            type: "success",
            title: "SUBJECT",
          });
          this.$router.push("/subject-list");
        })
        .catch((error) => {
          addMessage({
            message: "Update was not successful:" + error,
            type: "danger",
            title: "Update Subject",
          });
          this.$router.push("/subject-list");
        });
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
