<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px; margin-bottom: 50px;">
        <h3>Exam Period Details</h3>

        <MyInputComponent name="id" label="Exam Period Id" v-model="examPeriod.id" readonly></MyInputComponent>

        <MyInputComponent name="name" label="Name" v-model="examPeriod.name" readonly></MyInputComponent>

        <div class="label-left">
          <label class="form-label">Exam period from</label>
          <input type="date" v-model="examPeriod.examPeriodFrom" class="form-control" readonly/>
        </div>

        <div class="label-left">
          <label class="form-label">Exam period to</label>
          <input type="date" v-model="examPeriod.examPeriodTo" class="form-control" readonly/>
        </div>

        <button class="btn btn-outline-primary" @click="returnBack">Back to exam periods</button>

      </div>
    </div>
  </div>

</template>

<script>
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ExamPeriodService from "@/services/ExamPeriodService.js";

export default {
  components: { MyInputComponent },
  props: {
    examPeriodId: {
      type: String,
      required: true,
    },
  },
  created() {
    if (this.examPeriodId) {
      ExamPeriodService.getExamPeriod(this.examPeriodId).then((response) => {
        this.examPeriod = response.data;
      });
    }
  },
  data() {
    return {
      examPeriod: {}
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


