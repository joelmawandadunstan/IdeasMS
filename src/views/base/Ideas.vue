<template>
  <CCard>
    <h1>List Of Ideas</h1>
    <CDataTable :items="ideas" :fields="fields" striped caption="Idea Table">
      <template #Actions="{ item }">
        <td class="py-2">
          <CButton
            color="info"
            variant="outline"
            square
            size="sm"
            @click="{warningModal = true; propagateIdea(item);}"
          >
            Edit
          </CButton>
          <CButton
            color="danger"
            variant="outline"
            square
            size="sm"
            @click="deleteIdea(item)"
          >
            Delete
          </CButton>
          <CButton
            color="success"
            variant="outline"
            square
            size="sm"
            @click="toggleDetails(item)"
          >
            Attachment
          </CButton>

          <CButton
            color="warning"
            variant="outline"
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
          <CButton type="submit" color="success" @click="updateIdea">Edit</CButton
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
      fields: ["id", "idea_title", "idea_description", { key: "Actions" }],
      warningModal: false,
      updateForm: {
        id: "",
        idea_title: "",
        idea_description: "",
        categoryId: "",
        priorityId: "",
      },
    //   categoryId: [],
    //   priorityId: [],
     };
  },
  methods: {
    deleteIdea(item) {
      let deletingIdea = item.id;
      console.log(deletingIdea);

      axios
        .delete("/api/v1/ideas/delete/" + deletingIdea)
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
      this.updateForm.category = item.category;
      this.updateForm.priority = item.priority;
    },
    updateIdea() {
      axios
        .patch("/api/v1/ideas/edit/" + this.updateForm.id, this.updateForm)

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
      .get("/api/v1/ideas")
      .then((response) => {
        this.ideas = response.data;
        console.log(this.ideas);
      })
      .catch((error) => console.log(error));
    //   .then(data =>this.ideas = data)
  },
  created() {
    this.getLookUps();
  },
};
// response.json()
// .
</script>