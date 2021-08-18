<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px;">
        <h3 v-if="actionType === 'new'">New Exam Period</h3>
        <h3 v-else>Edit Exam Period</h3>

        <MyInputComponent name="id" label="Exam Period Id" v-model="examPeriod.id" :vcomp="v$.examPeriod.id" v-if="actionType !== 'new'" readonly></MyInputComponent>

        <MyInputComponent requiredField="true" name="name" label="Name" v-model="examPeriod.name" :vcomp="v$.examPeriod.name"></MyInputComponent>

        <div class="label-left">
          <label class="form-label required-field">Exam period from</label>
          <input type="date" v-model="examPeriod.examPeriodFrom" class="form-control"/>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Exam period to</label>
          <input type="date" v-model="examPeriod.examPeriodTo" class="form-control"/>
        </div>

        <button class="btn btn-outline-primary" @click="saveExamPeriod" :disabled='!isComplete'>Save</button>
        <button class="btn btn-outline-danger cancel" @click="returnBack">Cancel</button>

      </div>
    </div>
  </div>

</template>

<script>
import useValidate from "@vuelidate/core";
import { required } from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamPeriodService from "@/services/ExamPeriodService.js";

export default {
  components: { MyInputComponent },
  props: {
    examPeriodId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    this.vueLoader();
    if (this.examPeriodId) {
      ExamPeriodService.getExamPeriod(this.examPeriodId).then((response) => {
        this.examPeriod = response.data;
      });
    }
  },
  data() {
    return {
      v$: useValidate(),
      examPeriod: {}
    };
  },
  validations() {
    return {
      examPeriod:{
        id: { required },
        name: { required },
        examPeriodFrom: { required },
        examPeriodTo: { required }   
      }
    };
  },
  computed: {
    isComplete () {
      if(this.actionType == 'new')
        return this.examPeriod.name && this.examPeriod.examPeriodFrom && this.examPeriod.examPeriodTo;
        else
          return this.examPeriod.id && this.examPeriod.name && this.examPeriod.examPeriodFrom && this.examPeriod.examPeriodTo;
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
    saveExamPeriod() {
      if (this.actionType && this.actionType === "new") {
        this.insertExamPeriod();
      } else {
        this.updateExamPeriod();
      }
    },
    insertExamPeriod() {
      ExamPeriodService.insertExamPeriod(this.examPeriod)
        .then((response) => {
          console.log("Exam period inserted" + response.data);
         
          this.$toast.show("Exam period inserted!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/exam-period-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateExamPeriod() {
      ExamPeriodService.editExamPeriod(this.examPeriod)
        .then((response) => {
          console.log("Exam period updated" + response.data);
          
          this.$toast.show("Exam period updated!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/exam-period-list");
        })
        .catch((error) => {         
          console.log(error);
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


