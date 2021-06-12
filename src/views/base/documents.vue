<template>
  <CCard>
    <h1>List Of Documents</h1>
    <CDataTable
      :items="documents"
      :fields="fields"
      striped
      caption="Documents Table"
      :items-per-page="5"
      :pagination="{ doubleArrows: false, align: 'center' }"
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
                propagateDocument(item);
              }
            "
          >
            Edit
          </CButton>&#160;
          <CButton
            color="danger"
            square
            size="sm"
            @click="deleteDocument(item)"
          >
            Delete
          </CButton>&#160;
         
          <CButton >
            <router-link class="btn btn-success" to="/base/UploadFiles" role="button"
              >Attachment</router-link
            >
          </CButton>

          <CButton
            color="warning"
            square
            size="sm"
            @click="toggleDetails(item)"
          >
            AddNotes
          </CButton>
        </td>
      </template>
    </CDataTable>
    <div>
      <CModal title="Update Document" color="success" :show.sync="warningModal">
         <CInput
          v-model="updateForm.name"
          placeholder="name"
          type="text"
          autocomplete="name"
        >
          <template #prepend-content><CIcon name="cil-user" /></template>
        </CInput>
        <!-- <form>
          <div class="form-group">
            <label for="exampleFormControlFile1">file Upload</label>
            <input
              type="file"
              class="form-control-file"
              id="exampleFormControlFile1"
            />
          </div>
        </form> -->

        <CInput v-model="updateForm.id" placeholder="id" type="hidden" />
        <template #footer>
          <CButton type="submit" color="success">Cancel</CButton>
          <CButton type="submit" color="success" @click="updateDocument"
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
  name: "documents",
  components: {},
  data() {
    return {
      documents: [],
      fields: ["name", { key: "Actions" }],
      warningModal: false,
      updateForm: {
        id: "",
        name: "",
      },
    };
  },
  methods: {
    deleteDocument(item) {
      let deletingDocument = (item) = item.id;
      console.log(deletingDocument);

      axios.delete("/api/v1/documents/delete/" + deletingDocument, {
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
    propagateDocument(item) {
      this.updateForm.name = item.name;
      this.updateForm.id = item.id; //Do i have to update the id
    },
     updateDocument() {
      axios
        .patch("/api/v1/documents/edit/" + this.updateForm.id, this.updateForm,
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
  },
  mounted() {
    axios
      .get("/api/v1/documents", {
        headers: {
          //"Content-Type": "multipart/form-data",
          "content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((response) => {
        this.documents = response.data;
        console.log(this.documents);
      })
      .catch((error) => console.log(error));
  },
};
// response.json()
// .
</script>