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
                placeholder="Search by index number"
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
            <router-link to="/student-form" class="btn btn-primary"
              ><b-icon-plus />Add student</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">INDEX NUMBER</th>
          <th scope="col">INDEX YEAR</th>
          <th scope="col">FIRST NAME</th>
          <th scope="col">LAST NAME</th>
          <th scope="col">EMAIL NAME</th>
          <th scope="col">ADDRESS</th>
          <th scope="col">CITY</th>
          <th scope="col">CURRENT YEAR OF STUDY</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student of studentList" :key="student.indexNumber">
          <td>{{ student.indexNumber }}</td>
          <td>{{ student.indexYear }}</td>
          <td>{{ student.firstName }}</td>
          <td>{{ student.lastName }}</td>
          <td v-if="student.email">{{ student.email }}</td>
          <td v-else>None</td>
          <td v-if="student.adress">{{ student.adress }}</td>
          <td v-else>None</td>
          <td v-if="student.city">{{ student.city.name }}</td>
          <td v-else>None</td>
          <td>{{ student.currentYearOfStudy }}</td>
          <td>
            <router-link
              class="btn btn-info padd"
              :to="{
                name: 'StudentDetails',
                params: { studentId: student.indexNumber },
              }"
              ><b-icon-info-circle
            /></router-link>

            <router-link
              class="btn btn-success padd"
              :to="{
                name: 'EditStudent',
                params: { studentId: student.indexNumber },
              }"
              ><b-icon-pencil
            /></router-link>

            <button
              class="btn btn-danger padd"
              type="button"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="prepareToDeleteStudent(student)"
            >
              <b-icon-trash />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <PaginationComponent
      :refresh="refresh"
      type="student"
      :search="search"
      @data-changed="updateTable"
    ></PaginationComponent>
  </div>

  <DeleteComponent
    ref="deleteComponent"
    :title="`Delete confirmation`"
    :message="`Are you sure you want to delete student `"
    @deleteSelected="deleteSelectedStudent"
  >
  </DeleteComponent>
</template>

<script>
import StudentService from "@/services/StudentService.js";
import DeleteComponent from "@/components/delete/DeleteComponent.vue";
import PaginationComponent from "@/components/pagination/PaginationComponent.vue";

export default {
  components: { DeleteComponent, PaginationComponent },
  inject: ["GStore"],
  provide: {
    service: StudentService,
  },
  data() {
    return {
      studentList: [],
      selectedStudent: null,
      refresh: true,
      search: ""
    };
  },

  methods: {
    updateTable(studentList) {
      this.studentList = studentList;
    },
    prepareToDeleteStudent(student) {
      this.selectedStudent = student;
      this.$refs.deleteComponent.setSelected(
        this.selectedStudent.firstName + " " + this.selectedStudent.lastName
      );
    },
    deleteSelectedStudent() {
      StudentService.delete(this.selectedStudent.indexNumber).then(
        (response) => {
          console.log("Student deleted" + response.data);

          this.$toast.show("Student deleted!", {
            type: "success",
            duration: 6000,
          });
          this.refresh = !this.refresh;
        }
      );
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
