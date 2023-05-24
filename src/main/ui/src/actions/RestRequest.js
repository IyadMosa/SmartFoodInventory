import { toastr } from "react-redux-toastr";

export const RestRequest =
  (url, method, body, successMessage) => (dispatch, getState) => {
    const requestOptions = {
      method: method,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      ...(method && method !== "GET" ? { body: JSON.stringify(body) } : {}),
    };
    return fetch(url, requestOptions).then((response) =>
      response.status >= 400
        ? response
            .text()
            .then((data) => (toastr.error(data), Promise.reject(data)))
        : (!successMessage ? null : toastr.success(successMessage + " success"),
          response.headers.get("Content-Type").includes("text")
            ? response.text()
            : response.json())
    );
  };

export const RestRequestWithSave =
  (url, method, body, fileName) => (dispatch, getState) => {
    console.log(url);
    const requestOptions = {
      method: method,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getState().userInfo.jwt,
      },
      ...(method && method !== "GET" ? { body: JSON.stringify(body) } : {}),
    };

    return fetch(url, requestOptions).then((response) => {
      response.blob().then((blob) => {
        let url = window.URL.createObjectURL(blob);
        let a = document.createElement("a");
        a.href = url;
        a.download = fileName;
        a.click();
      });
    });
  };
