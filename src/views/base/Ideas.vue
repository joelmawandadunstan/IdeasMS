<template>
  <CCard>
    <h1>List Of Ideas</h1>
    <CDataTable 
    :items="ideas" 
    :fields="fields" 
    striped caption="Idea Table"
    :items-per-page="5"
    :pagination="{ doubleArrows: true, align: 'center' }"

    >
      <template #Actions="{ item }">
        <td class="py-2">
          <CButton
            color="info"
            square
            size="sm"
            @click="
              {
                warningModal = true;
                propagateIdea(item);
              }
            "
          >
            Edit
          </CButton>&#160;
          <CButton
            color="danger"
            square
            size="sm"
            @click="deleteIdea(item)"
          >
            Delete
          </CButton>

          <CButton >
            <router-link class="btn btn-success" to="/base/UploadFiles" role="button"
              >Attachment</router-link
            >
          </CButton>

          <CButton
            color="danger"
            square
            size="sm"
            @click="deleteIdea(item)"
          >
            AddNotes
          </CButton>&nbsp;

          <CButton
            color="warning"
            square
            size="sm"
            @click="printWindow()"
          >
            Print Ideas
          </CButton>
        </td>
      </template>
    </CDataTable>
    <div>
      <CModal title="Update Idea" color="success" :show.sync="warningModal">
        <CInput
          v-model="updateForm.idea_title"
          placeholder="Idea title"
          type="text"
          autocomplete="idea_title"
        >
          <template #prepend-content><CIcon name="cil-user" /></template>
        </CInput>

        <CTextarea
          v-model="updateForm.idea_description"
          label="Idea Description"
          placeholder="Content..."
          type="text"
          autocomplete="idea_description"
          horizontal
          rows="9"
        />

        <CRow>
          <CCol sm="6">
            <CSelect
              label="Category"
              :options="category"
              :value.sync="updateForm.categoryId"
            />
          </CCol>

          <CCol sm="6">
            <CSelect
              label="Priority"
              :options="priority"
              :value.sync="updateForm.priorityId"
            />
          </CCol>
        </CRow>
        <CInput v-model="updateForm.id" placeholder="id" type="hidden" />
        <template #footer>
          <CButton type="submit" color="success">Cancel</CButton>
          <CButton type="submit" color="success" @click="updateIdea"
            >Edit</CButton
          >
        </template>
      </CModal>
    </div>
  </CCard>
</template>

<script>
//import usersData from "../users/UsersData";
import axios from "axios";

export default {
  name: "Ideas",
  components: {},
  data() {
    return {
      ideas: [],
      category: [],
      priority: [],
      fields: ["idea_title", "idea_description", { key: "Actions" }],
      warningModal: false,
      updateForm: {
        id: "",
        idea_title: "",
        idea_description: "",
        categoryId: "",
        priorityId: "",
      },
    };
  },
  methods: {
    printWindow: function () {        
          window.print();
      },
     deleteIdea(item) {
      let deletingIdea = item.id;
      console.log(deletingIdea);

      axios.delete("/api/v1/ideas/delete/" + deletingIdea, {
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((response) => {
        // Event.fire("updated");
      })
      .catch((error) => {
        console.error(error);
      });
    },
    propagateIdea(item) {
      this.updateForm.idea_title = item.idea_title;
      this.updateForm.idea_description = item.idea_description;
      this.updateForm.id = item.id;
      this.updateForm.categoryId = item.categoryId;
      this.updateForm.priorityId = item.priorityId;
    },
    updateIdea() {
      axios
        .patch("/api/v1/ideas/edit/" + this.updateForm.id, this.updateForm,
        {
           headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
    },
        })
        .then((response) => {
          // Event.fire("updated");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getLookUps() {
      const reqOne = axios.get("/api/v1/category");
      const reqTwo = axios.get("/api/v1/priority");

      axios.all([reqOne, reqTwo]).then(
        axios.spread((category, priority) => {
          this.category = category.data.map((singleCategory) => {
            return {
              value: singleCategory.id,
              label: singleCategory.categoryName,
            };
          });
          this.priority = priority.data.map((singlePriority) => {
            return {
              value: singlePriority.id,
              label: singlePriority.priorityName,
            };
          });
        })
      );
    },
  },
  mounted() {
    axios
      .get("/api/v1/ideas", {
        headers: {
          //"Content-Type": "multipart/form-data",
          "content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((response) => {
        this.ideas = response.data;
        console.log(this.ideas);
      })
      .catch((error) => console.log(error));
  },
  created() {
    this.getLookUps();
  },
};
// response.json()
// .
</script>

<style lang="scss" scoped>
@media print {
 .c-header{
   visibility: hidden;
 }
 .c-footer{
   visibility: hidden;
 }
.data-v-431cdece{
  visibility: hidden;
}
}
@page{  
        size: potrait; /*Set the print size*/
        margin: 0 ;  /*Remove the above watermark*/
      }
</style>