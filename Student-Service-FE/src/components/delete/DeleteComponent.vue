 <template>
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
          <h5 class="modal-title" id="staticBackdropLabel">
            {{ this.title }}
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <span>{{ this.message }} {{ this.selected }} ?</span>
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
            @click="deleteSelected()"
          >
            Yes
          </button>
        </div>
      </div>
    </div>
    <hr class="featurette-divider">
  </div>
</template>
<script>
export default {
  inject: ["service"],
  props: {
    title: {
      type: String,
    },
    message: {
      type: String,
    },
  },
  data: function () {
    return {
      selected: "",
    };
  },
  methods: {
    setSelected: function (value) {
      this.selected = value;
    },
    deleteSelected() {
      this.$emit("deleteSelected");
    },
  },
};
</script>