<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px; margin-bottom: 50px;">
        <h3>Exam Registration - DETAILS</h3>

        <MyInputComponent name="id" label="Registration Id" v-model="examRegistration.id" readonly></MyInputComponent>
        
        <div class="label-left">
          <label class="form-label">Student</label>
          <select class="form-control" v-model="examRegistration.student" readonly>
            <option v-for="student in studentList" :value="student" :key="student.indexNumber">
              {{ student.firstName + ' ' + student.lastName }}
            </option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label">Exam for subject</label>
          <select class="form-control" v-model="examRegistration.exam" readonly>
            <option v-for="exam in examList" :value="exam" :key="exam.id">
              {{ exam.subject.name }}
            </option>
          </select>
        </div>
        
        <div class="label-left">
          <label class="form-label">Professor</label>
          <input type="text" :value="professor.firstName + ' ' + professor.lastName" readonly class="form-control"/>
        </div>

        <MyInputComponent name="id" label="Exam date" v-model="examDate" readonly></MyInputComponent>

        <MyInputComponent name="id" label="Exam period" v-model="examPeriod.name" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">Exam Registration Time</label>
          <input type="date" v-model="examRegistration.examRegistrationTime" readonly class="form-control"/>
        </div>

        <button class="btn btn-outline-primary" @click="returnBack">Back to exam registrations</button>

      </div>
    </div>
  </div>

</template>

<script>
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamRegistrationService from "@/services/ExamRegistrationService.js";

export default {
  components: { MyInputComponent },
  props: {
    examRegistrationId: {
      type: String,
      required: true,
    },
  },
  created() {
    if (this.examRegistrationId) {
      ExamRegistrationService.getExamRegistration(this.examRegistrationId).then((response) => {
        this.examRegistration = response.data;
        const examReg = response.data;
        this.professor.id = examReg.exam.professor.id;
        this.professor.firstName = examReg.exam.professor.firstName;
        this.professor.lastName = examReg.exam.professor.lastName;
        this.examDate = examReg.exam.examDate;
        this.examPeriod.id = examReg.exam.examPeriod.id;
        this.examPeriod.name = examReg.exam.examPeriod.name;
        this.examPeriod.examPeriodFrom = examReg.exam.examPeriod.examPeriodFrom;
        this.examPeriod.examPerio = examReg.exam.examPeriod.examPerio;
      });
    }
    this.getAllStudents();
    this.getAllExams();
  },
  data() {
    return {
      professor: {
        id: null,
        firstName: "",
        lastName: ""
      },
      examDate: null,
      examPeriod: {
        id: null,
        name: "",
        examPeriodFrom: null,
        examPerio: null
      },
      studentList: [],
      examList: [],
      examRegistration:{}
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
    getAllStudents() {
      ExamRegistrationService.getAllStudents().then((response) => {
        console.log(response);
        this.studentList = response.data;
      });
    },
    getAllExams() {
      ExamRegistrationService.getAllExams().then((response) => {
        console.log(response);
        this.examList = response.data;
      });
    }
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

