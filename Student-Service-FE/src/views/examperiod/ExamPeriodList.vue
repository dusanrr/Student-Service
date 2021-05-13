<template>
  <div class="col-8 col-md-10 col-sm-12 ml-2 justify" style="margin: auto; padding-top: 50px;">
    <table class="table">
      <thead>
        <tr>
          <th colspan="12">
            <router-link to="/exam-period-form"
            class="btn btn-primary"
              ><b-icon-plus />Add exam period</router-link
            >
          </th>
        </tr>
        <tr>
          <th scope="col">#</th>
          <th scope="col">EXAM PERIOD ID</th>
          <th scope="col">NAME</th>
          <th scope="col">EXAM PERIOD FROM</th>
          <th scope="col">EXAM PERIOD TO</th>
          <th scope="col">ACTIONS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(examPeriod, index) of examPeriodList" :key="examPeriod.id">
          <th scope="row">{{ currentPage * pageSize + index + 1 }}</th>
          <td>{{ examPeriod.id }}</td>
          <td>{{ examPeriod.name }}</td>
          <td>{{ format_date(examPeriod.examPeriodFrom) }}</td>
          <td>{{ format_date(examPeriod.examPeriodTo) }}</td>
          <td>
            <router-link
              :to="{
                name: 'EditExamPeriod',
                params: { examPeriodId: examPeriod.id },
              }"
              class="btn btn-success"
              ><b-icon-pencil
            /></router-link>
            <button
              class="btn btn-danger"
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
          Are you sure you want to delete exam period with ID
          <span v-if="selectedExamPeriod">{{ selectedExamPeriod.id }}</span>
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
            @click="deleteSelectedExamPeriod()"
          >
            Yes
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ExamPeriodService from "@/services/ExamPeriodService.js";
import { addMessage } from "@/main.js";
import moment from 'moment';

export default {
  inject: ["GStore"],
  data() {
    return {
      examPeriodList: [],
      selectedExamPeriod: null,
      currentPage: 0,
      totalPages: 0,
      pageSize: 3,
      pageLinks: [],
    };
  },

  created() {
    const pageState = this.$store.getters.getExamPeriodListState;
    if (pageState) {
      this.currentPage = pageState.page;
      this.pageSize = pageState.size;
      this.totalPages = pageState.totalPages;
    }
    this.loadPage();
  },

  beforeUnmount() {
    this.$store.dispatch("saveExamPeriodListPageState", {
      page: this.currentPage,
      size: this.pageSize,
      totalPages: this.totalPages,
    });
  },
  methods: {
    format_date(value) {
        if (value) {
        return moment(String(value)).format('DD/MM/YYYY')
        }
    },
    prepareToDeleteExamPeriod(examPeriod) {
      this.selectedExamPeriod = examPeriod;
    },
    deleteSelectedExamPeriod() {
      ExamPeriodService.delete(this.selectedExamPeriod.id)
        .then((response) => {
          console.log(response.data);
          addMessage({
            type: "success",
            title: "Delete exam period",
            message: "Exam period deleted",
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
      ExamPeriodService.getExamPeriodsByPage({
        page: this.currentPage,
        size: this.pageSize,
      }).then((response) => {
        console.log(response);
        this.totalPages = response.data.totalPages;
        this.pageSize = response.data.size;
        this.createPageLinks();
        this.examPeriodList = response.data.content;
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