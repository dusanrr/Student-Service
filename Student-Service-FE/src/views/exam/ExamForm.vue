<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px;">
        <h3 v-if="actionType === 'new'">New Exam</h3>
        <h3 v-else>Edit Exam</h3>

        <MyInputComponent name="id" label="Exam Id" v-model="exam.id" :vcomp="v$.exam.id" v-if="actionType !== 'new'" readonly></MyInputComponent>
        
        <div class="label-left">
          <label class="form-label required-field">Subject</label>
          <select class="form-control" v-model="exam.subject">
            <option v-for="subject in subjectList" :value="subject" :key="subject.id">
              {{ subject.name }}
            </option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Professor</label>
          <select class="form-control" v-model="exam.professor">
            <option v-for="professor in professorList" :value="professor" :key="professor.id">
              {{ professor.firstName }} {{ professor.lastName }}
            </option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Exam date</label>
          <input type="date" v-model="exam.examDate" class="form-control"/>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Exam period</label>
          <select class="form-control" v-model="exam.examPeriod">
            <option v-for="examPeriod in examPeriodList" :value="examPeriod" :key="examPeriod.id">
              {{ examPeriod.name }}
            </option>
          </select>
        </div>

        <button class="btn btn-outline-primary" @click="saveExam" :disabled='!isComplete'>Save</button>
        <button class="btn btn-outline-danger cancel" @click="returnBack">Cancel</button>

      </div>
    </div>
  </div>

</template>

<script>
import useValidate from "@vuelidate/core";
import { required } from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamService from "@/services/ExamService.js";

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
    this.vueLoader();
    if (this.examId) {
      ExamService.getExam(this.examId).then((response) => {
        this.exam = response.data;
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
      exam:{}
    };
  },
  validations() {
    return {
      exam: {
        id: { required },
        subject: { required },
        professor: { required },
        examDate: { required },
        examPeriod: { required }
      }
    };
  },
  computed: {
    isComplete () {
      if(this.actionType == 'new')
        return this.exam.subject && this.exam.professor && this.exam.examDate && this.exam.examPeriod;
        else
          return this.exam.id && this.exam.subject && this.exam.professor && this.exam.examDate && this.exam.examPeriod;
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
    saveExam() {
      if (this.actionType && this.actionType === "new") {
        this.insertExam();
      } else {
        this.updateExam();
      }
    },
    insertExam() {
      ExamService.insertExam(this.exam)
        .then((response) => {
          console.log("Exam inserted" + response.data);
          
          this.$toast.show("Exam inserted!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/exam-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateExam() {
      ExamService.editExam(this.exam)
        .then((response) => {
          console.log("Exam updated" + response.data);
          
          this.$toast.show("Exam updated!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/exam-list");
        })
        .catch((error) => {
          console.log(error);
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

