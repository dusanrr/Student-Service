<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px;">
        <h3 v-if="actionType === 'new'">Exam Registration - NEW</h3>
        <h3 v-else>Exam Registration - EDIT</h3>

        <MyInputComponent name="id" label="Registration Id" v-model="examRegistration.id" :vcomp="v$.examRegistration.id" v-if="actionType !== 'new'" readonly></MyInputComponent>
        
        <div class="label-left">
          <label class="form-label required-field">Student</label>
          <select class="form-control" v-model="examRegistration.student" @change="getStudentExam">
            <option v-for="student in studentList" :value="student" :key="student.indexNumber">
              {{ student.firstName + ' ' + student.lastName + ' - ' + student.indexNumber }}
            </option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Exam for subject</label>
          <select class="form-control" v-model="examRegistration.exam">
            <option v-for="exam in examList" :value="exam" :key="exam.id">
              {{ exam.subject.name }}
            </option>
          </select>
        </div>

        <button class="btn btn-outline-primary" @click="saveExamRegistration" :disabled='!isComplete'>Save</button>
        <button class="btn btn-outline-danger cancel" @click="returnBack">Cancel</button>

      </div>
    </div>
  </div>

</template>

<script>
import useValidate from "@vuelidate/core";
import { required} from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamRegistrationService from "@/services/ExamRegistrationService.js";

export default {
  components: { MyInputComponent },
  props: {
    examRegistrationId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    this.vueLoader();
    if (this.examRegistrationId) {
      ExamRegistrationService.getExamRegistration(this.examRegistrationId).then((response) => {
        this.examRegistration = response.data;
      });
    }
    this.getAllStudents();
  },
  data() {
    return {
      v$: useValidate(),
      studentList: [],
      examList: [],
      examRegistration:{}
    };
  },
  validations() {
    return {
      examRegistration: {
        id: { required },
        student: { required },
        exam: { required },
      }
    };
  },
  computed: {
    isComplete () {
      if(this.actionType == 'new')
        return this.examRegistration.student && this.examRegistration.exam;
        else
          return this.examRegistration.id && this.examRegistration.student && this.examRegistration.exam;
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
    saveExamRegistration() {
      if (this.actionType && this.actionType === "new") {
        this.insertExamRegistration();
      } else {
        this.updateExamRegistration();
      }
    },
    insertExamRegistration() {
      ExamRegistrationService.insertExamRegistration(this.examRegistration)
        .then((response) => {
          console.log("Exam Registration inserted" + response.data);

          this.$toast.show("Exam Registration inserted!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/exam-registration-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateExamRegistration() {
      ExamRegistrationService.editExamRegistration(this.examRegistration)
        .then((response) => {
          console.log("Exam Registration updated" + response.data);

          this.$toast.show("Exam Registration updated!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/exam-registration-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getAllStudents() {
      ExamRegistrationService.getAllStudents().then((response) => {
        console.log(response);
        this.studentList = response.data;
      });
    },
    getStudentExam() {
      ExamRegistrationService.getStudentExam(this.examRegistration.student.indexNumber).then((response) => {
        console.log(response);
        this.examList = response.data;
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

