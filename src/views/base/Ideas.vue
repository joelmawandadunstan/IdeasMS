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
            @click="loadModal(item)"
          >
            Delete
          </CButton>

          <CButton >
            <router-link class="btn btn-success" to="/base/UploadFiles" role="button"
              >Attachment</router-link
            >
          </CButton>

          <CButton
            color="primary"
            square
            size="sm"
            @click="
              {
                primaryModal = true;
                propagateNote(item);
                
              }
            "
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
          </CButton>&nbsp;

          <CButton
            color="info"
            square
            size="sm"
            @click="infoModal = true;
            propNotes(item);"
          >
            View Notes
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
          <CButton @click="warningModal = false" color="success">Cancel</CButton>
          <CButton type="submit" color="success" @click="updateIdea"
            >Edit</CButton
          >
        </template>
      </CModal>

      <CModal title="Add Notes" color="primary" :show.sync="primaryModal">
        <CInput
          v-model="note.content"
          placeholder="AddNote"
          type="text"
          autocomplete="AddNote"
        >   
        </CInput>

        <CInput v-model="note.idea_id" placeholder="id" type="hidden"/>
        <template #footer>
          <CButton @click="primaryModal = false" color="primary">Cancel</CButton>
          <CButton type="submit" color="primary" @click="addNote"
            >AddNote</CButton
          >
        </template>
      </CModal>
       <CModal title="Delete Idea" color="danger" :show.sync="dangerModal">
              <b>Are you sure you want to delete this Idea?</b>
              <template #footer>
                <CButton @click="dangerModal = false" color="danger">Cancel</CButton>
                <CButton type="submit" color="danger" @click="deleteIdea(item)"
                  >Delete</CButton
                >
              </template>
            </CModal>
         <CModal title="Notes" color="ifo" :show.sync="infoModal">
              <ul v-for="note in notes" :key="note.id">
                <li>
                  {{note.content}}
                </li>
              </ul>
              <template #footer>
                <CButton @click="infoModal = false" color="info">Back</CButton>
                <!-- <CButton type="submit" color="info" @click="j"></CButton
                > -->
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
      notes: [],
      ideas: [],
      category: [],
      priority: [],
      content: "",
      fields: ["idea_title", "idea_description",{ key: "Actions" }],
      warningModal: false,
      primaryModal: false,
      dangerModal: false,
      infoModal: false,
      note: {
        idea_id: "",
        content: ""
      },
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
    propNotes(item){
      this.notes = item.notes
    },
    loadModal(item){
      this.dangerModal=true;
      this.item = item;
    },
    propagateNote(item) {
      this.note.idea_id = item.id
    },
    addNote() {
      // const data = {
      //   content: this.content
      // };
      console.log(this.note);
      axios
        .post(`api/v1/notes`, this.note, {
          headers: {
            "content-type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          },
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    },
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
        this.ideas = response.data
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