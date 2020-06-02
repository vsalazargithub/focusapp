import React from "react";
import TextField from "material-ui/TextField";
import RaisedButton from "material-ui/RaisedButton";
import axios from 'axios';

export default class Form extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      vmBoxId: "",
      vmBoxIdError: ""
    };
  }


  change = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  validate = () => {
    let isError = false;
    const errors = {
      vmBoxIdError: ""
    };

    if (this.state.vmBoxId === undefined || this.state.vmBoxId === "" || this.state.vmBoxId.length <= 0) {
      isError = true;
      errors.vmBoxIdError = "Voicemail Box required";
    }

    this.setState({
      ...this.state,
      ...errors
    });

    return isError;
  };

  onSubmit = e => {
    e.preventDefault();
    const err = this.validate();
    if (!err) {
      this.fetchData();
    }
  };

  fetchData() {

    axios.get('http://localhost:8080/v2/accounts/4642e64040cdb8b89c310a21a07c7f62/vmboxes/' + this.state.vmBoxId + '/messages?paginate=true')
      .then((response) => {
        let data = response.data.data;
        this.props.upateState(data);
        console.log(response.data.data);
      })
      .catch((error) => {
        console.log(error);
      })

  }

  render() {
    return (
      <form>
        <TextField
          name="vmBoxId"
          hintText="Voicemail Box"
          floatingLabelText="Voicemail Box"
          value={this.state.vmBoxId}
          onChange={e => this.change(e)}
          errorText={this.state.vmBoxIdError}
          floatingLabelFixed
        />
        <br />
        <RaisedButton label="Submit" onClick={e => this.onSubmit(e)} primary />
      </form>
    );
  }
}
