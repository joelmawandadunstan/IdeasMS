import axios from 'axios'

class UploadFilesService {
  upload(file, onUploadProgress) {
    let formData = new FormData();

    formData.append("file", file);

    return axios.post("api/v1/documents", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        //"content-type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
      },
      onUploadProgress
    });
  }

  getFiles() {
    return axios.get("api/v1/documents",
    {
      headers: {
        "Content-Type": "multipart/form-data",
        //"content-type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
      },
    });
  }
}

export default new UploadFilesService();