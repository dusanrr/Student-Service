<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px;">
        <h3 v-if="actionType === 'new'">New Subject</h3>
        <h3 v-else>Edit Subject</h3>

        <MyInputComponent name="id" label="Subject Id" v-model="subject.id" :vcomp="v$.subject.id" v-if="actionType !== 'new'" readonly></MyInputComponent>

        <MyInputComponent requiredField="true" name="name" label="Name" v-model="subject.name" :vcomp="v$.subject.name"></MyInputComponent>

        <MyInputComponent name="description" label="Description" v-model="subject.description" :vcomp="v$.subject.description"></MyInputComponent>

        <MyInputComponent requiredField="true" name="noOfESP" label="No. of ESP" v-model="subject.noOfESP" :vcomp="v$.subject.noOfESP"></MyInputComponent>

        <MyInputComponent name="yearOfStudy" label="Year of study" v-model="subject.yearOfStudy" :vcomp="v$.subject.yearOfStudy"></MyInputComponent>

        <div class="label-left">
          <label class="form-label">Semester</label>
          <select class="form-control" v-model="subject.semester">
            <option value="Summer">Summer</option>
            <option value="Winter">Winter</option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Professor</label>
           <select class="form-control" v-model="subject.professor">
            <option v-for="professor in professorList" :value="professor" :key="professor.id">
              {{ professor.firstName + ' ' + professor.lastName }}
            </option>
          </select>
        </div>

        <button class="btn btn-outline-primary" @click="saveSubject" :disabled='!isComplete'>Save</button>
        <button class="btn btn-outline-danger cancel" @click="returnBack">Cancel</button>
      </div>
    </div>
  </div>

</template>

<script>
import useValidate from "@vuelidate/core";
import { required, minLength, maxLength } from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import SubjectService from "@/services/SubjectService.js";

export default {
  components: { MyInputComponent },
  props: {
    subjectId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    this.vueLoader();
    if (this.subjectId) {
      SubjectService.getSubject(this.subjectId).then((response) => {
        this.subject = response.data;
      });
    }
    this.getAllProfessors();
  },
  data() {
    return {
      v$: useValidate(),
      professorList: [],
      subject: {}
    };
  },
  validations() {
    return {
      subject: {
        id: { required },
        name: { required, minLength: minLength(3), maxLength: maxLength(30) },
        description: { minLength: minLength(1), maxLength: maxLength(30) },
        noOfESP: { minLength: minLength(1), maxLength: maxLength(30) },
        yearOfStudy: { maxLength: maxLength(1) },
        semester: { maxLength: maxLength(10) },
      }
    };
  },
  updated(){
    
  },
  computed: {
    isComplete () {
      if(this.actionType == 'new')
        return this.subject.name && this.subject.noOfESP && this.subject.professor;
      else
      return this.subject.id && this.subject.name && this.subject.noOfESP && this.subject.professor;
    }
  },
  methods: {
    returnBack() {
      this.$router.go(-1);
    },
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
    saveSubject() {
      if (this.actionType && this.actionType === "new") {
        this.insertSubject();
      } else {
        this.updateSubject();
      }
    },
    insertSubject() {
      SubjectService.insertSubject(this.subject)
        .then((response) => {
          console.log("Subject inserted" + response.data);

          this.$toast.show("Subject inserted!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/subject-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateSubject() {
      SubjectService.editSubject(this.subject)
        .then((response) => {
          console.log("Subject updated" + response.data);

          this.$toast.show("Subject updated!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/subject-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getAllProfessors() {
      SubjectService.getAllProfessors().then((response) => {
        console.log(response);
        this.professorList = response.data;
      });
    },
  },
};
</script>

<style scoped>
.label-left
{
  text-align: left;
  padding-bottom: 10px;
}

img{
  height: 200px;
  width: 200px;
  margin-top: 30px;
}

.cancel{
  margin-left: 10px;
}
</style>
