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
                placeholder="Search by name"
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
            <router-link to="/exam-period-form" class="btn btn-primary"
              ><b-icon-plus />Add exam period</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">EXAM PERIOD ID</th>
          <th scope="col">NAME</th>
          <th scope="col">EXAM PERIOD FROM</th>
          <th scope="col">EXAM PERIOD TO</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="examPeriod of examPeriodList" :key="examPeriod.id">
          <td>{{ examPeriod.id }}</td>
          <td>{{ examPeriod.name }}</td>
          <td>{{ format_date(examPeriod.examPeriodFrom) }}</td>
          <td>{{ format_date(examPeriod.examPeriodTo) }}</td>
          <td>
            <router-link
              :to="{
                name: 'ExamPeriodDetails',
                params: { examPeriodId: examPeriod.id },
              }"
              class="btn btn-info padd"
              ><b-icon-info-circle
            /></router-link>

            <router-link
              :to="{
                name: 'EditExamPeriod',
                params: { examPeriodId: examPeriod.id },
              }"
              class="btn btn-success padd"
              ><b-icon-pencil
            /></router-link>

            <button
              class="btn btn-danger padd"
              type="button"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="prepareToDeleteExamPeriod(examPeriod)"
            >
              <b-icon-trash />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <PaginationComponent
      :refresh="refresh"
      type="examPeriod"
      :search="search"
      @data-changed="updateTable"
    ></PaginationComponent>
  </div>

  <DeleteComponent
    ref="deleteComponent"
    :title="`Delete confirmation`"
    :message="`Are you sure you want to delete exam period `"
    @deleteSelected="deleteSelectedExamPeriod"
  >
  </DeleteComponent>
</template>

<script>
import ExamPeriodService from "@/services/ExamPeriodService.js";
import DeleteComponent from "@/components/delete/DeleteComponent.vue";
import PaginationComponent from "@/components/pagination/PaginationComponent.vue";
import moment from "moment";

export default {
  components: { DeleteComponent, PaginationComponent },
  inject: ["GStore"],
  provide: {
    service: ExamPeriodService,
  },
  data() {
    return {
      examPeriodList: [],
      selectedExamPeriod: null,
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
    updateTable(examPeriodList) {
      this.examPeriodList = examPeriodList;
    },
    prepareToDeleteExamPeriod(examPeriod) {
      this.selectedExamPeriod = examPeriod;
      this.$refs.deleteComponent.setSelected(
        this.selectedExamPeriod.name + "(ID:" + this.selectedExamPeriod.id + ")"
      );
    },
    deleteSelectedExamPeriod() {
      ExamPeriodService.delete(this.selectedExamPeriod.id).then((response) => {
        console.log("Exam period deleted" + response.data);

        this.$toast.show("Exam period deleted!", {
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