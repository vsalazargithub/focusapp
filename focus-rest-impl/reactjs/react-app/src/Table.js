import React from "react";
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from "material-ui/Table";
import EditIcon from "material-ui/svg-icons/image/edit";
import CheckIcon from "material-ui/svg-icons/navigation/check";
import TextField from "material-ui/TextField";
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

const row = (
  x,
  i,
  header,
  startEditing,
  editIdx,
  mediaID,
  handleChange,
  stopEditing
) => {
  const currentlyEditing = editIdx === i;
  return (
    <TableRow key={`tr-${i}`} selectable={false}>
      {header.map((y, k) => (
        <TableRowColumn key={`trc-${k}`}>
          {currentlyEditing && y.prop === "folder" ? (
            <SelectField
              name={y.prop}
              onChange={e => handleChange(e, y.prop, i)}
              value={x[y.prop]} >
              <MenuItem value={'new'} primaryText="New" />
              <MenuItem value={'saved'} primaryText="Saved" />
              <MenuItem value={'deleted'} primaryText="Deleted" />
            </SelectField>
          ) : (
              x[y.prop]
            )}
        </TableRowColumn>
      ))}
      <TableRowColumn>
        {currentlyEditing ? (
          <CheckIcon onClick={() => stopEditing()} />
        ) : (
            <EditIcon onClick={() => startEditing(i, x['media_id'])} />
          )}
      </TableRowColumn>
    </TableRow>
  );
};

export default ({
  data,
  header,
  startEditing,
  editIdx,
  mediaID,
  handleChange,
  stopEditing
}) => (
    <form>
      <Table>
        <TableHeader>
          <TableRow>
            {header.map((x, i) => (
              <TableHeaderColumn key={`thc-${i}`}>{x.name}</TableHeaderColumn>
            ))}
            <TableHeaderColumn />
          </TableRow>
        </TableHeader>
        <TableBody>
          {data.map((x, i) =>
            row(
              x,
              i,
              header,
              startEditing,
              editIdx,
              mediaID,
              handleChange,
              stopEditing
            )
          )}
        </TableBody>
      </Table>
    </form>
  );
