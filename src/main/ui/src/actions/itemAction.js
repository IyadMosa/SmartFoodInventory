import { RestRequest } from "./RestRequest";
import {
  ITEMS,
  ITEMS_ERROR,
  REQUESTED_SHARED_ITEMS,
  SHARE_ITEM_FAILED,
  SHARE_ITEM_SUCCESS,
  SHARED_ITEMS,
  TO_CONFIRM_SHARED_ITEMS,
} from "./types";

export const getStoreItemsAll = () => (dispatch, getState) =>
  RestRequest(
    "/api/items",
    "GET",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: ITEMS, payload: data });
    })
    .catch((error) => {
      dispatch({ type: ITEMS_ERROR, payload: error.message });
    });

export const getSharedStoreItemsAll = (radius) => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/available/${radius}`,
    "GET",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: SHARED_ITEMS, payload: data });
    })
    .catch((error) => {
      dispatch({ type: ITEMS_ERROR, payload: error.message });
    });

export const shareItem = (itemId) => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/share/${itemId}`,
    "POST",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: SHARE_ITEM_SUCCESS });
    })
    .catch((error) => {
      dispatch({ type: SHARE_ITEM_FAILED });
    });

export const requestSharedItem = (sharedItemId) => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/request/${sharedItemId}`,
    "POST",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: SHARE_ITEM_SUCCESS });
    })
    .catch((error) => {
      dispatch({ type: SHARE_ITEM_FAILED });
    });

export const confirmSharedItem = (sharedItemId) => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/confirm/${sharedItemId}`,
    "POST",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: SHARE_ITEM_SUCCESS });
    })
    .catch((error) => {
      dispatch({ type: SHARE_ITEM_FAILED });
    });
export const getRequestedSharedItemsAll = () => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/requested`,
    "GET",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: REQUESTED_SHARED_ITEMS, payload: data });
    })
    .catch((error) => {
      dispatch({ type: ITEMS_ERROR, payload: error.message });
    });
export const geToConfirmSharedItemsAll = () => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/to-confirm`,
    "GET",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: TO_CONFIRM_SHARED_ITEMS, payload: data });
    })
    .catch((error) => {
      dispatch({ type: ITEMS_ERROR, payload: error.message });
    });
