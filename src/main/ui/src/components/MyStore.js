import React, { useEffect } from "react";
import { TableScreen } from "@iyadmosa/react-library";
import { useDispatch, useSelector } from "react-redux";
import { getStoreItemsAll, shareItem } from "../actions/itemAction";
import IconButton from "@material-ui/core/IconButton";
import ShareIcon from "@mui/icons-material/Share";

const MyStore = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getStoreItemsAll());
  }, []);
  const data = useSelector((state) => state.items.itemsStore);
  const columns = [
    {
      Header: "id",
      accessor: "id",
    },
    {
      Header: "name",
      accessor: "name",
    },
    {
      Header: "barcode",
      accessor: "barcode",
    },
    {
      Header: "category",
      accessor: "category",
    },
    {
      Header: "manufacturer",
      accessor: "manufacturer",
    },
    {
      Header: "expirationDate",
      accessor: "expirationDate",
    },
    {
      Header: "addedAt",
      accessor: "addedAt",
    },
    {
      Header: "productionCountry",
      accessor: "productionCountry",
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
                dispatch(shareItem(porps.original.id));
              }}
            >
              <ShareIcon titleAccess={"Share item"} />
            </IconButton>
          </div>
        );
      },
      width: 100,
    },
  ];
  return (
    <TableScreen
      title={"My Store"}
      data={data}
      columns={columns}
      addForm={<div />}
      onAddSubmit={() => alert("submit")}
      onInit={() => dispatch(getStoreItemsAll())}
      disabledSubmit={true}
    />
  );
};

export default MyStore;
