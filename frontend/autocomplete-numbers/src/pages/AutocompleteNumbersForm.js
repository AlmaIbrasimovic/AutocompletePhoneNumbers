import React, { Component } from 'react'
import axios from 'axios'
import Dropdown from 'react-dropdown';
import './style.css'
import 'react-dropdown/style.css';
import TextField from '@mui/material/TextField';
export default class AutocompleteNumbersForm extends Component {
    constructor(props) {
        super(props)
        this.state = {
            PhoneNumbers: [
                { Name: "Temp", phoneNumber: false },

            ],
            phoneNumbers: [],
            searchQuery: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentWillMount() {
        /*axios.get('http://localhost:8080/api/v1/phone-numbers/autocomplete?query=0981')
            .then(res => {
                const phoneNumbers = res.data.content;
                this.setState({ phoneNumbers });
        })*/
    }

    handleChange(event) {
        this.setState({ searchQuery: event.target.value });
    }

    showAutocompletedPhoneNumber() {
        return this.state.phoneNumbers.map((phoneNumbers, index) => {
            const { name } = phoneNumbers
            const { phoneNumber } = phoneNumbers
            return (
                <tr key={phoneNumber}>
                    <td>{name}</td>
                    <td>{phoneNumber}</td>
                </tr>
            )
        })
    }

    tableHeader() {
        let header = Object.keys(this.state.PhoneNumbers[0])
        return header.map((key, index) => {
            return <th key={index}>{key.toUpperCase()}</th>
        })
    }

    handleSubmit(event) {
        this.setState.phoneNumbers = []
        axios.get('http://localhost:8080/api/v1/phone-numbers/autocomplete', {
            params: {
              query: this.state.searchQuery
            }
          }).then(res => {
            const phoneNumbers = res.data.content;
            this.setState({ phoneNumbers });
        })
        event.preventDefault();
      }

    render() {
        return (
            <div>
                <h2 id = 'title'>Autocompleting phone numbers</h2>
                <form id ='queryForm' onSubmit={this.handleSubmit}>
                    <label id = 'labelQuery'>
                        Enter search query:
                        <input type="text" onChange={this.handleChange} />
                    </label>
                    <input type="submit" value="Search" />
                </form>
              
                <div className="mainDiv">
                    <table id='phoneNumbersList'>
                        <tbody>
                            <tr>{this.tableHeader()}</tr>
                            {this.showAutocompletedPhoneNumber()}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}