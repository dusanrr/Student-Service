<template>
 <img alt="Student service" src="../../assets/student-srvc.png">
  <div class="col-8 col-md-10 col-sm-12 ml-2 justify" style="margin: auto; padding-top:50px;">
  
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
                placeholder="Search by student index number"
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
            <router-link to="/exam-registration-form"
            class="btn btn-primary"
              ><b-icon-plus />Registration for exam</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">EXAM REGISTRATION ID</th>
          <th scope="col">STUDENT</th>
          <th scope="col">SUBJECT</th>
          <th scope="col">PROFESSOR</th>
          <th scope="col">EXAM DATE</th>
          <th scope="col">EXAM PERIOD</th>
          <th scope="col">EXAM REGISTRATION TIME</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="examRegistration of examRegistrationList" :key="examRegistration.id">
          <td>{{ examRegistration.id }}</td>
          <td>{{ examRegistration.student.firstName + ' ' + examRegistration.student.lastName + ' - '+ examRegistration.student.indexNumber }}</td>
          <td>{{ examRegistration.exam.subject.name }}</td>
          <td>{{ examRegistration.exam.professor.firstName + ' ' + examRegistration.exam.professor.lastName }}</td>
          <td>{{ format_date(examRegistration.exam.examDate) }}</td>
          <td>{{ examRegistration.exam.examPeriod.name }}</td>
          <td>{{ format_date(examRegistration.examRegistrationTime) }}</td>
          <td>
            <router-link
              :to="{
                name: 'ExamRegistrationDetails',
                params: { examRegistrationId: examRegistration.id },
              }"
              class="btn btn-info padd"
              ><b-icon-info-circle
            /></router-link>

            <router-link
              :to="{
                name: 'EditExamRegistration',
                params: { examRegistrationId: examRegistration.id },
              }"
              class="btn btn-success padd"
              ><b-icon-pencil
            /></router-link>

            <button
              class="btn btn-danger padd"
              type="button"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="prepareToDeleteExamRegistration(examRegistration)"
            >
              <b-icon-trash />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

     <PaginationComponent
      :refresh="refresh"
      type="examRegistration"
      :search="search"
      @data-changed="updateTable"
    ></PaginationComponent>

  </div>

  <DeleteComponent
    ref="deleteComponent"
    :title="`Delete confirmation`"
    :message="`Are you sure you want to delete exam registration `"
    @deleteSelected="deleteSelectedExamRegistration"
  >
  </DeleteComponent>

</template>

<script>
import ExamRegistrationService from "@/services/ExamRegistrationService.js";
import DeleteComponent from "@/components/delete/DeleteComponent.vue";
import PaginationComponent from "@/components/pagination/PaginationComponent.vue";
import moment from 'moment';

export default {
  components: { DeleteComponent, PaginationComponent },
  inject: ["GStore"],
  provide: {
    service: ExamRegistrationService,
  },
  data() {
    return {
      examRegistrationList: [],
      selectedExamRegistration: null,
      refresh: true,
      search: ""
    };
  },
  methods: {
    format_date(value) {
      if (value) {
        return moment(String(value)).format('DD/MM/YYYY')
      }
    },
    updateTable(examRegistrationList) {
      this.examRegistrationList = examRegistrationList;
    },
    prepareToDeleteExamRegistration(examRegistration) {
      this.selectedExamRegistration = examRegistration;
      this.$refs.deleteComponent.setSelected(
        "(ID:"+ this.selectedExamRegistration.id + ")" + ", Subject:" + this.selectedExamRegistration.exam.subject.name + " and Student:" + this.selectedExamRegistration.student.firstName + " " + this.selectedExamRegistration.student.lastName
      );
    },
    deleteSelectedExamRegistration() {
      ExamRegistrationService.delete(this.selectedExamRegistration.id)
        .then((response) => {
          console.log("Exam Registration deleted" + response.data);

          this.$toast.show("Exam Registration deleted!", {
                    type: "success",
                    duration: 6000
                  });
          this.refresh = !this.refresh;
        });
    }
  }
};

</script>
<style scoped>
button {
  margin-left: 5px;
}

img{
  height: 240px;
  width: 300px;
  margin-top: 30px;
}

.padd{
  margin-left: 3px;
  margin-right: 3px;
}
</style>