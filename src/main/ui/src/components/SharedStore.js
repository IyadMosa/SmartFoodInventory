import React, { useEffect, useState } from "react";
import { Button, TableScreen, TextField } from "@iyadmosa/react-library";
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
  const [radius, setRadius] = useState(1);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getSharedStoreItemsAll(radius));
  }, []);
  const data = useSelector((state) => state.items.sharedItems);
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
    <di>
      <Button
        onClick={() => {
          setRadius(1);
          dispatch(getSharedStoreItemsAll(1));
        }}
        label={"1 Km"}
      />
      <Button
        onClick={() => {
          setRadius(5);
          dispatch(getSharedStoreItemsAll(5));
        }}
        label={"5 Km"}
      />
      <Button
        onClick={() => {
          setRadius(10);
          dispatch(getSharedStoreItemsAll(10));
        }}
        label={"10 Km"}
      />
      <TableScreen
        title={"Shared Store"}
        data={data}
        columns={columns}
        addForm={<div />}
        onAddSubmit={() => alert("submit")}
        onInit={() => dispatch(getSharedStoreItemsAll(radius))}
        disabledSubmit={true}
      />
    </di>
  );
};

export default SharedStore;
