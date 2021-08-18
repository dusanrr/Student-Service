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
                placeholder="Search by first and last name"
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
            <router-link to="/professor-form" class="btn btn-primary"
              ><b-icon-plus />Add professor</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">PROFESSOR ID</th>
          <th scope="col">FIRST NAME</th>
          <th scope="col">LAST NAME</th>
          <th scope="col">EMAIL NAME</th>
          <th scope="col">ADDRESS</th>
          <th scope="col">CITY</th>
          <th scope="col">PHONE</th>
          <th scope="col">REELECTION DATE</th>
          <th scope="col">TITLE</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="professor of professorList" :key="professor.id">
          <td>{{ professor.id }}</td>
          <td>{{ professor.firstName }}</td>
          <td>{{ professor.lastName }}</td>
          <td v-if="professor.email">{{ professor.email }}</td>
          <td v-else>None</td>
          <td v-if="professor.adress">{{ professor.adress }}</td>
          <td v-else>None</td>
          <td v-if="professor.city">{{ professor.city.name }}</td>
          <td v-else>None</td>
          <td v-if="professor.phone">{{ professor.phone }}</td>
          <td v-else>None</td>
          <td>{{ format_date(professor.reelectionDate) }}</td>
          <td>{{ professor.title.titleName }}</td>
          <td>
            <router-link
              class="btn btn-info padd"
              :to="{
                name: 'ProfessorDetails',
                params: { professorId: professor.id },
              }"
              ><b-icon-info-circle
            /></router-link>

            <router-link
              :to="{
                name: 'EditProfessor',
                params: { professorId: professor.id },
              }"
              class="btn btn-success"
              ><b-icon-pencil
            /></router-link>
            <button
              class="btn btn-danger"
              type="button"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="prepareToDeleteProfessor(professor)"
            >
              <b-icon-trash />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <PaginationComponent
      :refresh="refresh"
      type="professor"
      :search="search"
      @data-changed="updateTable"
    ></PaginationComponent>
  </div>

  <DeleteComponent
    ref="deleteComponent"
    :title="`Delete confirmation`"
    :message="`Are you sure you want to delete professor `"
    @deleteSelected="deleteSelectedProfessor"
  >
  </DeleteComponent>
</template>
<script>
import ProfessorService from "@/services/ProfessorService.js";
import DeleteComponent from "@/components/delete/DeleteComponent.vue";
import PaginationComponent from "@/components/pagination/PaginationComponent.vue";
import moment from "moment";

export default {
  components: { DeleteComponent, PaginationComponent },
  inject: ["GStore"],
  provide: {
    service: ProfessorService,
  },
  data() {
    return {
      professorList: [],
      selectedProfessor: null,
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
    updateTable(professorList) {
      this.professorList = professorList;
    },
    prepareToDeleteProfessor(professor) {
      this.selectedProfessor = professor;
      this.$refs.deleteComponent.setSelected(
        this.selectedProfessor.firstName +
          " " +
          this.selectedProfessor.lastName +
          "(ID:" +
          this.selectedProfessor.id +
          ")"
      );
    },
    deleteSelectedProfessor() {
      ProfessorService.delete(this.selectedProfessor.id).then((response) => {
        console.log("Professor deleted" + response.data);

        this.$toast.show("Professor deleted!", {
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