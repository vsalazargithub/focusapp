import React, { Component } from "react";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import injectTapEventPlugin from "react-tap-event-plugin";

import logo from "./logo.svg";
import "./App.css";
import Form from "./Form";
import Table from "./Table";
import NavBar from './NavBar';
import axios from 'axios'

injectTapEventPlugin();

class App extends Component {
  state = {
    data: [],
    editIdx: -1,
    mediaID: "",
    newStatus: ""
  };

  componentDidMount() {
    this.fetchData();
  }

  fetchData() {
    axios.get('http://localhost:8080/v2/accounts/4642e64040cdb8b89c310a21a07c7f62/vmboxes/b37675a2d7b90d60f0ee5d4175502394/messages?paginate=true')
      .then((response) => {
        this.setState({ data: response.data.data });
      })
      .catch((error) => {
        console.log(error);
      })
  }

  updateStatus() {
    let urhi = 'http://localhost:8080/v2/accounts/4642e64040cdb8b89c310a21a07c7f62/vmboxes/b37675a2d7b90d60f0ee5d4175502394/messages/' + this.state.mediaID + "?folder=" + this.state.newStatus;

    axios.post(urhi).then((response) => {
      console.log(response);
    })
      .catch((error) => {
        console.log(error);
      })
  }

  startEditing = (i, media_id) => {
    this.setState({ editIdx: i, mediaID: media_id });
  };

  stopEditing = () => {
    this.updateStatus();
    this.setState({ editIdx: -1 });
  };

  handleChange = (e, name, i) => {
    let value  = e.target.innerText.toLowerCase();
    this.setState({ newStatus: value });
    this.setState(state => ({
      data: state.data.map(
        (row, j) => (j === i ? { ...row, [name]: value } : row)
      )
    }));
  };

  upateState = (data) => {
    this.setState({ data: data });
  }

  render() {
    return (
      <MuiThemeProvider>
        <div className="App">
          <NavBar />
          <br /><br />
          
          <Form
            upateState={this.upateState}
            onSubmit={submission =>
              this.setState({
                data: [...this.state.data, submission]
              })}
          />
          <Table
            startEditing={this.startEditing}
            editIdx={this.state.editIdx}
            mediaID={this.state.mediaID}
            stopEditing={this.stopEditing}
            handleChange={this.handleChange}
            data={this.state.data}
            header={[
              {
                name: "Status",
                prop: "folder"
              },
              {
                name: "From",
                prop: "from"
              },
              {
                name: "To",
                prop: "to"
              },
              {
                name: "Duration",
                prop: "timestamp"
              }
            ]}
          />
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
