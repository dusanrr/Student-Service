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
            <router-link to="/subject-form" class="btn btn-primary"
              ><b-icon-plus />Add subject</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">SUBJECT ID</th>
          <th scope="col">NAME</th>
          <th scope="col">DESCRIPTION</th>
          <th scope="col">NO. OF ESP</th>
          <th scope="col">YEAR OF STUDY</th>
          <th scope="col">SEMESTER</th>
          <th scope="col">PROFESSOR</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="subject of subjectList" :key="subject.id">
          <td>{{ subject.id }}</td>
          <td>{{ subject.name }}</td>
          <td v-if="subject.description">{{ subject.description }}</td>
          <td v-else>None</td>
          <td>{{ subject.noOfESP }}</td>
          <td v-if="subject.yearOfStudy">{{ subject.yearOfStudy }}</td>
          <td v-else>None</td>
          <td v-if="subject.semester">{{ subject.semester }}</td>
          <td v-else>None</td>
          <td>
            {{ subject.professor.firstName }} {{ subject.professor.lastName }}
          </td>
          <td>
            <router-link
              :to="{
                name: 'SubjectDetails',
                params: { subjectId: subject.id },
              }"
              class="btn btn-info padd"
              ><b-icon-info-circle
            /></router-link>

            <router-link
              :to="{
                name: 'EditSubject',
                params: { subjectId: subject.id },
              }"
              class="btn btn-success padd"
              ><b-icon-pencil
            /></router-link>

            <button
              class="btn btn-danger padd"
              type="button"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="prepareToDeleteSubject(subject)"
            >
              <b-icon-trash />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <PaginationComponent
      :refresh="refresh"
      type="subject"
      :search="search"
      @data-changed="updateTable"
    ></PaginationComponent>
  </div>

  <DeleteComponent
    ref="deleteComponent"
    :title="`Delete confirmation`"
    :message="`Are you sure you want to delete subject `"
    @deleteSelected="deleteSelectedSubject"
  >
  </DeleteComponent>
</template>

<script>
import SubjectService from "@/services/SubjectService.js";
import DeleteComponent from "@/components/delete/DeleteComponent.vue";
import PaginationComponent from "@/components/pagination/PaginationComponent.vue";
import moment from "moment";

export default {
  components: { DeleteComponent, PaginationComponent },
  inject: ["GStore"],
  provide: {
    service: SubjectService,
  },
  data() {
    return {
      subjectList: [],
      selectedSubject: null,
      refresh: true,
      search: "",
    };
  },
  methods: {
    format_date(value) {
      if (value) {
        return moment(String(value)).format("DD/MM/YYYY");
      }
    },
    updateTable(subjectList) {
      this.subjectList = subjectList;
    },
    prepareToDeleteSubject(subject) {
      this.selectedSubject = subject;
      this.$refs.deleteComponent.setSelected(
        this.selectedSubject.name + "(ID:" + this.selectedSubject.id + ")"
      );
    },
    deleteSelectedSubject() {
      SubjectService.delete(this.selectedSubject.id).then((response) => {
        console.log("Subject deleted" + response.data);

        this.$toast.show("Subject deleted!", {
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