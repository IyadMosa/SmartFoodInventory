import React, { useEffect } from "react";
import { TableScreen } from "@iyadmosa/react-library";
import { useDispatch, useSelector } from "react-redux";
import {
  acceptItem,
  getSharedStoreItemsAll,
  getStoreItemsAll,
  shareItem,
} from "../actions/itemAction";
import IconButton from "@material-ui/core/IconButton";
import SaveAltIcon from "@material-ui/icons/SaveAlt";

const SharedStore = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getSharedStoreItemsAll());
  }, []);
  const data = useSelector((state) => state.items.sharedItems);
  console.log(data);
  const columns = [
    {
      Header: "id",
      accessor: "id",
    },
    {
      Header: "consumed",
      accessor: "consumed",
    },
    {
      Header: "quantity",
      accessor: "quantity",
    },
    {
      Header: "sharingDate",
      accessor: "sharingDate",
    },
    {
      Header: "shared item name",
      accessor: "item.name",
    },
    {
      Header: "shared item id",
      accessor: "item.id",
    },
    {
      Header: "shared item expirationDate",
      accessor: "item.expirationDate",
    },
    {
      Header: "",
      filterable: false,
      sortable: false,
      resizable: false,
      Cell: (porps) => {
        return (
          <div>
            <IconButton
              color="primary"
              onClick={() => {
                dispatch(acceptItem(porps.original.id));
              }}
            >
              <SaveAltIcon titleAccess={"accept item"} />
            </IconButton>
          </div>
        );
      },
      width: 100,
    },
  ];
  return (
    <TableScreen
      title={"Shared Store"}
      data={data}
      columns={columns}
      addForm={<div />}
      onAddSubmit={() => alert("submit")}
      onInit={() => dispatch(getSharedStoreItemsAll())}
      disabledSubmit={true}
    />
  );
};

export default SharedStore;
