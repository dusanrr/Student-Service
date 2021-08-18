<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px; margin-bottom: 50px;">
        <h3>Exam Details</h3>

        <MyInputComponent name="id" label="Exam Id" v-model="id" readonly></MyInputComponent>

        <MyInputComponent name="subject" label="Subject" v-model="subject.name" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">Professor</label>
          <input type="text" class="form-control" :value="professor.firstName + ' ' + professor.lastName" readonly/>
        </div>

        <div class="label-left">
          <label class="form-label">Exam date</label>
          <input type="date" v-model="examDate" class="form-control" readonly/>
        </div>

        <div class="label-left">
          <label class="form-label">Exam period</label>
          <input type="text" class="form-control" :value="examPeriod.name" readonly/>
        </div>

        <button class="btn btn-outline-primary" @click="returnBack">Back to exams</button>

      </div>
    </div>
  </div>

</template>

<script>
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamService from "@/services/ExamService.js";

export default {
  components: { MyInputComponent },
  props: {
    examId: {
      type: String,
      required: true,
    },
  },
  created() {
    if (this.examId) {
      ExamService.getExam(this.examId).then((response) => {
        const exam = response.data;
        this.id = exam.id;
        this.subject.id = exam.subject.id;
        this.subject.name = exam.subject.name;
        this.professor.id = exam.professor.id;
        this.professor.firstName = exam.professor.firstName;
        this.professor.lastName = exam.professor.lastName;
        this.examDate = exam.examDate;
        this.examPeriod.id = exam.examPeriod.id;
        this.examPeriod.name = exam.examPeriod.name;
        this.examPeriod.examPeriodFrom = exam.examPeriod.examPeriodFrom;
        this.examPeriod.examPerio = exam.examPeriod.examPerio;
      });
    }
    this.getAllProfessors();
    this.getAllSubjects();
    this.getAllExamPeriods();
  },
  data() {
    return {
      professorList: [],
      subjectList: [],
      examPeriodList: [],
      id: null,
      subject: {
        id: null,
        name: ""
      },
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

