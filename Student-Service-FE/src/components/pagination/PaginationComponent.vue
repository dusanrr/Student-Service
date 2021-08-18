<template>
     <div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" @click="goToPage(0)">First</a>
        </li>
        <li class="page-item">
          <a class="page-link" @click="previousPage()">Previous</a>
        </li>
        <li
          class="page-item"
          :class="{ active: pageNum === currentPage }"
          v-for="pageNum of pageLinks"
          :key="pageNum"
        >
          <a class="page-link" @click="goToPage(pageNum)">{{ pageNum }}</a>
        </li>
        <li class="page-item">
          <a class="page-link" @click="nextPage()">Next</a>
        </li>
        <li class="page-item">
          <a class="page-link" @click="goToLastPage()">
            Last
          </a>
        </li>
      </ul>
    </nav>
  </div>

</template>

<script>
export default {
  inject: ["service"],
  props: {
    type: {
      type: String,
      default: "",
    },
    refresh: {
      type: Boolean,
      default: true,
    },
    search: {
      type: String,
      default: "",
    }
  },
  data() {
    return {
      dataList: [],
      currentPage: 0,
      totalPages: 0,
      pageSize: 4,
      pageLinks: []
    };
  },
  watch: {
    refresh: function () {
      this.loadPage();
    },
  },
  created() {

    const pageState = this.$store.getters.getListStateByType(this.type);

    if (pageState) {
      this.currentPage = pageState.page;
      this.pageSize = pageState.size;
      this.totalPages = pageState.totalPages;
    }

    this.loadPage();
  },
  updated()
  {
    this.loadPage();
  },
  mounted() {
    this.vueLoader();
  },
  beforeUnmount() {
    this.$store.dispatch("saveListState", {
      page: this.currentPage,
      size: this.pageSize,
      totalPages: this.totalPages,
      type: this.type,
    });
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
    loadPage() {
      this.service
        .getAllByPage({
          page: this.currentPage,
          size: this.pageSize,
          search: this.search
        })
        .then((response) => {
          this.totalPages = response.data.totalPages;
          this.pageSize = response.data.size;
          this.createPageLinks();
          this.dataList = response.data.content;
        });

      setTimeout(() => {
        this.$emit("data-changed", this.dataList);
      }, 100);
    },
    previousPage() {
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
    goToPage(page) {
      if (this.currentPage !== page) {
        this.currentPage = page;
        this.loadPage();
      }
    },
    goToLastPage() {
      var page = this.totalPages -1;
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
          ? this.totalPages > 2  ? this.currentPage  - 2 : this.currentPage  - 1
          : this.currentPage - 1;
        const numberOfLinks = this.totalPages > 3 ? 3 : this.totalPages;
      this.pageLinks = [...Array(numberOfLinks).keys()].map((x) => firsPage + x);
    },
  },
};
</script>

<style scoped>
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
