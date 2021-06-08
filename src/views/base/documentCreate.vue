<template>
  <div>
    <!-- <head>
      ...
      <link
        type="text/css"
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      />
    </head> -->
    <div v-if="currentFile" class="progress">
      <div
        class="progress-bar progress-bar-info progress-bar-striped"
        role="progressbar"
        :aria-valuenow="progress"
        aria-valuemin="0"
        aria-valuemax="100"
        :style="{ width: progress + '%' }"
      >
        {{ progress }}%
      </div>
    </div>

    <label class="btn btn-default">
      <input type="file" ref="file" @change="selectFile" />
    </label>

    <button class="btn btn-success" :disabled="!selectedFiles" @click="upload">
      Upload
    </button>

    <div class="alert alert-light" role="alert">{{ message }}</div>

    <div class="card">
      <div class="card-header">List of Files</div>
      <ul class="list-group list-group-flush">
        <li
          class="list-group-item"
          v-for="(file, index) in fileInfos"
          :key="index"
        >
          <a :href="file.url">{{ file.name }}</a>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "documentCreate",
  data() {
    return {
      selectedFiles: undefined,
      currentFile: undefined,
      progress: 0,
      message: "",

      fileInfos: [],
      //   name: '',
    };
  },
  methods: {
    // addDocument() {
    //   axios({
    //     method: "POST",
    //     url: "api/v1/documents",
    //     data: {
    //       name: this.name,
    //     },
    //   })
    //     .then((response) => console.log(response))
    //     .catch((error) => console.log(error));
    // },
    selectFile() {
      this.selectedFiles = this.documents;
    },
    upload() {
      this.progress = 0;

      this.currentFile = this.selectedFiles.item(0);
      documents
        .upload(this.currentFile, (event) => {
          this.progress = Math.round((100 * event.loaded) / event.total);
        })
        .then((response) => {
          this.message = response.data.message;
          return documents.getFiles();
        })
        .then((files) => {
          this.fileInfos = files.data;
        })
        .catch(() => {
          this.progress = 0;
          this.message = "Could not upload the file!";
          this.currentFile = undefined;
        });

      this.selectedFiles = undefined;
    },
  },
  mounted() {
    documents.getFiles().then((response) => {
      this.fileInfos = response.data;
    });
  },
};
</script>
