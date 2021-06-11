<template>
  <div>
    <h1>Create Ideas</h1>
    <CRow>
      <CCol md="12">
        <CCard>
          <CCardHeader> <strong>Create Your Ideas</strong> Here </CCardHeader>
          <CCardBody>
            <CForm @submit.prevent="addIdea">
              <CInput
                v-model="idea_title"
                label="Idea Title"
                description="type your idea titles"
                placeholder="Idea title"
                horizontal
              />

              <CSelect
                label="Category"
                horizontal
                :options="category"
                placeholder="category"
              />

              <CSelect
                label="Priority"
                horizontal
                :options="priority"
                placeholder="priority"
              />

              <CInput
                v-model="createDate"
                label="createDate"
                type="date"
                horizontal
              />

              <CTextarea
                v-model="idea_description"
                label="Idea Description"
                placeholder="Content..."
                horizontal
                rows="9"
              />

              <CButton type="submit" size="sm" color="primary"
                ><CIcon name="cil-check-circle" /> Submit</CButton
              >
            </CForm>
          </CCardBody>
          <CCardFooter> </CCardFooter>
        </CCard>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ideaCreate",
  data() {
    return {
      idea_title: "",
      idea_description: "",
      createDate: "",
      category: [],
      priority: [],
    };
  },
  methods: {
    getLookUps() {
      const reqOne = axios.get("/api/v1/category");
      const reqTwo = axios.get("/api/v1/priority");

      axios.all([reqOne, reqTwo]).then(
        axios.spread((category, priority) => {
           this.category = (category.data).map((singleCategory) => {
            return {
              value: singleCategory.id,
              label: singleCategory.categoryName,
            };
          });
           this.priority = (priority.data).map((singlePriority) => {
            return {
              value: singlePriority.id,
              label: singlePriority.priorityName,
            };
          });
        })
      );
    },

    addIdea() {
      const data = {
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        createDate: this.createDate,
      };
      axios
        .post(`api/v1/ideas`, data, {
          headers: {
            "content-type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          },
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    },
  },
  created() {
    this.getLookUps();
  },
};
</script>
