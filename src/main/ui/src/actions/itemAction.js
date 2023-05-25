import { RestRequest } from "./RestRequest";
import {
  ITEMS,
  ITEMS_ERROR,
  REGISTER_SUCCESS,
  SHARE_ITEM_FAILED,
  SHARE_ITEM_SUCCESS,
  SHARED_ITEMS,
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
      console.log(error);
      dispatch({ type: ITEMS_ERROR, payload: error.message });
    });

export const getSharedStoreItemsAll = (radius) => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/${radius}`,
    "GET",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: SHARED_ITEMS, payload: data });
    })
    .catch((error) => {
      console.log(error);
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
      console.log(error);
      dispatch({ type: SHARE_ITEM_FAILED });
    });

export const acceptItem = (sharedItemId) => (dispatch, getState) =>
  RestRequest(
    `/api/shared-items/accept/${sharedItemId}`,
    "POST",
    null,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      dispatch({ type: SHARE_ITEM_SUCCESS });
    })
    .catch((error) => {
      console.log(error);
      dispatch({ type: SHARE_ITEM_FAILED });
    });
