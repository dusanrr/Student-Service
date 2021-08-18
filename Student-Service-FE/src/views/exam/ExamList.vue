<template>
  <img alt="Student service" src="../../assets/student-srvc.png" />
  <div
    class="col-8 col-md-10 col-sm-12 ml-2 justify"
    style="margin: auto; padding-top: 50px"
  >
    <div class="container">
      <div class="row">
        <div class="col"></div>
        <div class="col-5">
          <div class="col-sm">
            <div class="input-group input-group-sm mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text bg-dark text-white" id="inputGroup-sizing-sm"
                  >Search</span
                >
              </div>
              <input
                type="text"
                class="form-control"
                aria-label="Small"
                aria-describedby="inputGroup-sizing-sm"
                placeholder="Search by subject name"
                v-model="search"
              />
            </div>
          </div>
        </div>
        <div class="col"></div>
      </div>
    </div>

    <table class="table table-hover table-light">
      <thead>
        <tr>
          <th colspan="12">
            <router-link to="/exam-form" class="btn btn-primary"
              ><b-icon-plus />Add exam</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">EXAM ID</th>
          <th scope="col">SUBJECT</th>
          <th scope="col">PROFESSOR</th>
          <th scope="col">EXAM DATE</th>
          <th scope="col">EXAM PERIOD</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="exam of examList" :key="exam.id">
          <td>{{ exam.id }}</td>
          <td>{{ exam.subject.name }}</td>
          <td>{{ exam.professor.firstName }} {{ exam.professor.lastName }}</td>
          <td>{{ format_date(exam.examDate) }}</td>
          <td>{{ exam.examPeriod.name }}</td>
          <td>
            <router-link
              :to="{
                name: 'ExamDetails',
                params: { examId: exam.id },
              }"
              class="btn btn-info padd"
              ><b-icon-info-circle
            /></router-link>

            <router-link
              :to="{
                name: 'EditExam',
                params: { examId: exam.id },
              }"
              class="btn btn-success padd"
              ><b-icon-pencil
            /></router-link>

            <button
              class="btn btn-danger padd"
              type="button"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="prepareToDeleteExam(exam)"
            >
              <b-icon-trash />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <PaginationComponent
      :refresh="refresh"
      type="exam"
      :search="search"
      @data-changed="updateTable"
    ></PaginationComponent>
  </div>

  <DeleteComponent
    ref="deleteComponent"
    :title="`Delete confirmation`"
    :message="`Are you sure you want to delete exam `"
    @deleteSelected="deleteSelectedExam"
  >
  </DeleteComponent>
</template>

<script>
import ExamService from "@/services/ExamService.js";
import DeleteComponent from "@/components/delete/DeleteComponent.vue";
import PaginationComponent from "@/components/pagination/PaginationComponent.vue";
import moment from "moment";

export default {
  components: { DeleteComponent, PaginationComponent },
  inject: ["GStore"],
  provide: {
    service: ExamService,
  },
  data() {
    return {
      examList: [],
      selectedExam: null,
      refresh: true,
      search: ""
    };
  },
  methods: {
    format_date(value) {
      if (value) {
        return moment(String(value)).format("DD/MM/YYYY");
      }
    },
    updateTable(examList) {
      this.examList = examList;
    },
    prepareToDeleteExam(exam) {
      this.selectedExam = exam;
      this.$refs.deleteComponent.setSelected(
        "(ID:" +
          this.selectedExam.id +
          ")" +
          " for subject " +
          this.selectedExam.subject.name
      );
    },
    deleteSelectedExam() {
      ExamService.delete(this.selectedExam.id).then((response) => {
        console.log("Exam deleted" + response.data);

        this.$toast.show("Exam deleted!", {
          type: "success",
          duration: 6000,
        });
        this.refresh = !this.refresh;
      });
    },
  },
};
</script>
<style scoped>
button {
  margin-left: 5px;
}

img {
  height: 240px;
  width: 300px;
  margin-top: 30px;
}

.padd {
  margin-left: 3px;
  margin-right: 3px;
}
</style>