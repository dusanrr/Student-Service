<template>
  <div class="form-container m-4 col-6 col-md-8 col-sm-10">
    <h3 v-if="actionType === 'new'">New Exam</h3>
    <h3 v-else>Edit Exam</h3>

    <MyInputComponent
      readonly
      name="id"
      label="Exam Period Id"
      v-model="id"
      :vcomp="v$.id"
      v-if="actionType !== 'new'"
    >
    </MyInputComponent>

    <MyInputComponent
      name="name"
      label="Name"
      v-model="name"
      :vcomp="v$.name"
    >
    </MyInputComponent>

    <label class="form-label">Exam period from</label>
    <input type="date" v-model="examPeriodFrom" class="form-control"/>

    <label class="form-label">Exam period to</label>
    <input type="date" v-model="examPeriodTo" class="form-control"/>

    <div class="d-flex flex-row-reverse">
      <button class="btn btn-outline-primary" @click="saveExamPeriod">Save</button>
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
import ExamPeriodService from "@/services/ExamPeriodService.js";
import { addMessage } from "@/main.js";

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
    if (this.examPeriodId) {
      ExamPeriodService.getExamPeriod(this.examPeriodId).then((response) => {
        const examPeriod = response.data;
        this.id = examPeriod.id;
        this.name = examPeriod.name;
        this.examPeriodFrom = examPeriod.examPeriodFrom;
        this.examPeriodTo = examPeriod.examPeriodTo;
      });
    }
  },
  data() {
    return {
      v$: useValidate(),
      id: null,
      name: "",
      examPeriodFrom: null,
      examPeriodTo: null
    };
  },
  validations() {
    return {
      id: {
        required
      },
      name: {
        required
      },
      examPeriodFrom: {
        required
      },
      examPeriodTo: {
        required
      }   
    };
  },
  methods: {
    saveExamPeriod() {
      if (this.actionType && this.actionType === "new") {
        this.insertExamPeriod();
      } else {
        this.updateExamPeriod();
      }
    },
    insertExamPeriod() {
      ExamPeriodService.insertExamPeriod({
        name: this.name,
        examPeriodFrom: this.examPeriodFrom,
        examPeriodTo: this.examPeriodTo
      })
        .then((response) => {
          console.log("Exam period inserted" + response);
          addMessage({
            message: "Exam period inserted",
            type: "success",
            title: "EXAM PERIOD",
          });
          this.$router.push("/exam-period-list");
        })
        .catch((error) => {
          addMessage({
            message: "Insert was not successful:" + error,
            type: "danger",
            title: "Insert exam period",
          });
          this.$router.push("/exam-period-list");
        });
    },
    updateExamPeriod() {
      ExamPeriodService.editExamPeriod({
        id: this.id,
        name: this.name,
        examPeriodFrom: this.examPeriodFrom,
        examPeriodTo: this.examPeriodTo
      })
        .then((response) => {
          console.log("Exam period updated" + response);
          addMessage({
            message: "Exam period updated",
            type: "success",
            title: "EXAM PERIOD",
          });
          this.$router.push("/exam-period-list");
        })
        .catch((error) => {
          addMessage({
            message: "Update was not successful:" + error,
            type: "danger",
            title: "Update exam period",
          });
          this.$router.push("/exam-period-list");
        });
    },
  },
};
</script>
