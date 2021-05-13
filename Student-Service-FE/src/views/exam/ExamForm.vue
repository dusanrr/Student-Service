<template>
  <div class="form-container m-4 col-6 col-md-8 col-sm-10">
    <h3 v-if="actionType === 'new'">New Exam</h3>
    <h3 v-else>Edit Exam</h3>

    <MyInputComponent
      readonly
      name="id"
      label="Exam Id"
      v-model="id"
      :vcomp="v$.id"
      v-if="actionType !== 'new'"
    >
    </MyInputComponent>
    
    <label class="form-label">Subject</label>
    <div>
      <select class="form-control" v-model="subjectDto">
        <option
          v-for="subject in subjectList"
          :value="subject"
          :key="subject.id"
        >
          {{ subject.name }}
        </option>
      </select>
    </div>

    <label class="form-label">Professor</label>
    <div>
      <select class="form-control" v-model="professorDto">
        <option
          v-for="professor in professorList"
          :value="professor"
          :key="professor.id"
        >
          {{ professor.firstName }} {{ professor.lastName }}
        </option>
      </select>
    </div>

    <label class="form-label">Exam date</label>
    <input type="date" v-model="examDate" class="form-control"/>

    <label class="form-label">Exam period</label>
    <div>
      <select class="form-control" v-model="examPeriodDto">
        <option
          v-for="examPeriod in examPeriodList"
          :value="examPeriod"
          :key="examPeriod.id"
        >
          {{ examPeriod.name }}
        </option>
      </select>
    </div>

    <div class="d-flex flex-row-reverse">
      <button class="btn btn-outline-primary" @click="saveExam">Save</button>
    </div>
  </div>
</template>

<script>
import useValidate from "@vuelidate/core";
import {
  required,
  //maxValue,
  //minLength,
  //maxLength,
} from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamService from "@/services/ExamService.js";
import { addMessage } from "@/main.js";

export default {
  components: { MyInputComponent },
  props: {
    examId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    if (this.examId) {
      ExamService.getExam(this.examId).then((response) => {
        const exam = response.data;
        this.id = exam.id;
        this.subjectDto.id = exam.subjectDto.id;
        this.subjectDto.name = exam.subjectDto.name;
        this.professorDto.id = exam.professorDto.id;
        this.professorDto.firstName = exam.professorDto.firstName;
        this.professorDto.lastName = exam.professorDto.lastName;
        this.examDate = exam.examDate;
        this.examPeriodDto.id = exam.examPeriodDto.id;
        this.examPeriodDto.name = exam.examPeriodDto.name;
        this.examPeriodDto.examPeriodFrom = exam.examPeriodDto.examPeriodFrom;
        this.examPeriodDto.examPeriodTo = exam.examPeriodDto.examPeriodTo;
      });
    }
    this.getAllProfessors();
    this.getAllSubjects();
    this.getAllExamPeriods();
  },
  data() {
    return {
      v$: useValidate(),
      professorList: [],
      subjectList: [],
      examPeriodList: [],
      id: null,
      subjectDto: {
        id: null,
        name: ""
      },
      professorDto: {
        id: null,
        firstName: "",
        lastName: ""
      },
      examDate: null,
      examPeriodDto: {
        id: null,
        name: "",
        examPeriodFrom: null,
        examPeriodTo: null
      },
    };
  },
  validations() {
    return {
      id: {
        required
      },
      subjectDto: {
        required
      },
      professorDto: {
        required
      },
      examDate: {
        required
      },
      examPeriodDto: {
        required
      }
    };
  },
  methods: {
    saveExam() {
      if (this.actionType && this.actionType === "new") {
        this.insertExam();
      } else {
        this.updateExam();
      }
    },
    insertExam() {
      ExamService.insertExam({
        subjectDto: {
          id: this.subjectDto.id,
          firstName: this.subjectDto.firstName,
          lastName: this.subjectDto.lastName,
        },
        professorDto: {
          id: this.professorDto.id,
          firstName: this.professorDto.name
        },
        examDate: this.examDate,
        examPeriodDto: {
          id: this.examPeriodDto.id,
          name: this.examPeriodDto.name,
          examPeriodFrom: this.examPeriodDto.examPeriodFrom,
          examPeriodTo: this.examPeriodDto.examPeriodTo
        }
      })
        .then((response) => {
          console.log("Exam inserted" + response);
          addMessage({
            message: "Exam inserted",
            type: "success",
            title: "EXAM",
          });
          this.$router.push("/exam-list");
        })
        .catch((error) => {
          addMessage({
            message: "Insert was not successful:" + error,
            type: "danger",
            title: "Insert exam",
          });
          this.$router.push("/exam-list");
        });
    },
    updateExam() {
      ExamService.editExam({
        id: this.id,
        subjectDto: {
          id: this.subjectDto.id,
          firstName: this.subjectDto.firstName,
          lastName: this.subjectDto.lastName,
        },
        professorDto: {
          id: this.professorDto.id,
          firstName: this.professorDto.name
        },
        examDate: this.examDate,
        examPeriodDto: {
          id: this.examPeriodDto.id,
          name: this.examPeriodDto.name,
          examPeriodFrom: this.examPeriodDto.examPeriodFrom,
          examPeriodTo: this.examPeriodDto.examPeriodTo
        }
      })
        .then((response) => {
          console.log("Exam updated" + response);
          addMessage({
            message: "Exam updated",
            type: "success",
            title: "EXAM",
          });
          this.$router.push("/exam-list");
        })
        .catch((error) => {
          addMessage({
            message: "Update was not successful:" + error,
            type: "danger",
            title: "Update exam",
          });
          this.$router.push("/exam-list");
        });
    },
    getAllProfessors() {
      ExamService.getAllProfessors().then((response) => {
        console.log(response);
        this.professorList = response.data;
      });
    },
    getAllSubjects() {
      ExamService.getAllSubjects().then((response) => {
        console.log(response);
        this.subjectList = response.data;
      });
    },
    getAllExamPeriods() {
      ExamService.getAllExamPeriods().then((response) => {
        console.log(response);
        this.examPeriodList = response.data;
      });
    },
  },
};
</script>
