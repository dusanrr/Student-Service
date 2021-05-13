<template>
  <div class="col-8 col-md-10 col-sm-12 ml-2 justify" style="margin: auto; padding-top:50px;">
    <table class="table">
      <thead>
        <tr>
          <th colspan="12">
            <router-link to="/student-form"
            class="btn btn-primary"
              ><b-icon-plus />Add student</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">#</th>
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
        <tr v-for="(student, index) of studentList" :key="student.indexNumber">
          <th scope="row">{{ currentPage * pageSize + index + 1 }}</th>
          <td>{{ student.indexNumber }}</td>
          <td>{{ student.indexYear }}</td>
          <td>{{ student.firstName }}</td>
          <td>{{ student.lastName }}</td>
          <td>{{ student.email }}</td>
          <td>{{ student.adress }}</td>
          <td>{{ student.cityDto.name }}</td>
          <td>{{ student.currentYearOfStudy }}</td>
          <td>
            <router-link
              class="btn btn-success"
              :to="{
                name: 'EditStudent',
                params: { studentId: student.indexNumber },
              }"
              ><b-icon-pencil
            /></router-link>
            <button
              class="btn btn-danger"
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

    <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" @click="gotoToPage(0)">First</a>
          </li>
          <li class="page-item">
            <a class="page-link" @click="prviosPage()">Previous</a>
          </li>
          <li
            class="page-item"
            :class="{ active: pageNum === currentPage }"
            v-for="pageNum of pageLinks"
            :key="pageNum"
          >
            <a class="page-link" @click="gotoToPage(pageNum)">{{ pageNum }}</a>
          </li>
          <li class="page-item">
            <a class="page-link" @click="nextPage()">Next</a>
          </li>
          <li class="page-item">
            <a class="page-link" @click="gotoToPage(totalPages - 1)">Last</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>

  <div
    class="modal fade"
    id="staticBackdrop"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          Are you sure you want to delete student with index
          <span v-if="selectedStudent">{{ selectedStudent.indexNumber }}</span>
          ?
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button
            type="button"
            class="btn btn-primary"
            data-bs-dismiss="modal"
            @click="deleteSelectedStudent()"
          >
            Yes
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import StudentService from "@/services/StudentService.js";
import { addMessage } from "@/main.js";

export default {
  inject: ["GStore"],
  data() {
    return {
      studentList: [],
      selectedStudent: null,
      currentPage: 0,
      totalPages: 0,
      pageSize: 3,
      pageLinks: [],
    };
  },

  created() {
    const pageState = this.$store.getters.getStudentListState;
    if (pageState) {
      this.currentPage = pageState.page;
      this.pageSize = pageState.size;
      this.totalPages = pageState.totalPages;
    }
    this.loadPage();
  },

  beforeUnmount() {
    this.$store.dispatch("saveStudentListPageState", {
      page: this.currentPage,
      size: this.pageSize,
      totalPages: this.totalPages,
    });
  },
  methods: {
    prepareToDeleteStudent(student) {
      this.selectedStudent = student;
    },
    deleteSelectedStudent() {
      StudentService.delete(this.selectedStudent.indexNumber)
        .then((response) => {
          console.log(response.data);
          addMessage({
            type: "success",
            title: "Delete student",
            message: "Student deleted",
          });
          this.loadPage();
        })
        .catch((error) => {
          addMessage({
            message: "Delete was not successful:" + error,
            type: "danger",
            title: "Delete",
          });
        });
    },
    loadPage() {
      StudentService.getStudentsByPage({
        page: this.currentPage,
        size: this.pageSize,
      }).then((response) => {
        console.log(response);
        this.totalPages = response.data.totalPages;
        this.pageSize = response.data.size;
        this.createPageLinks();
        this.studentList = response.data.content;
      });
    },
    prviosPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.loadPage();
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages - 1) {
        this.currentPage++;
        this.loadPage();
      }
    },
    gotoToPage(page) {
      console.log("page", page);
      if (this.currentPage !== page) {
        this.currentPage = page;
        this.loadPage();
      }
    },
    createPageLinks() {
      const firsPage =
        this.currentPage === 0
          ? this.currentPage
          : this.currentPage === this.totalPages - 1
          ? this.totalPages > 2
            ? this.currentPage - 2
            : this.currentPage - 1
          : this.currentPage - 1;
      const numberOfLinks = this.totalPages > 3 ? 3 : this.totalPages;
      this.pageLinks = [...Array(numberOfLinks).keys()].map(
        (x) => firsPage + x
      );
    },
  },
};
</script>
<style scoped>
button {
  margin-left: 5px;
}

.page-item {
  cursor: pointer;
}

.pagination > li > a
{
    background-color: white;
    color:#302f2f;
}

.pagination > li > a:focus,
.pagination > li > a:hover,
.pagination > li > span:focus,
.pagination > li > span:hover
{
    color: #302f2f;
    background-color: #eee;
    border-color: #ddd;
}

.pagination > .active > a
{
    color: white;
    background-color: #302f2f;
    border: solid 1px #302f2f;
}

.pagination > .active > a:hover
{
    background-color: #302f2f;
    border: solid 1px #302f2f;
}
</style>