'use strict';
import Modal from "react-modal";

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
const root = '/api';
// end::vars[]

class App extends React.Component {

    constructor(props){
        super(props);
        this.state={items:[], isOpen:false, newTitle:'',newDescription:''};
		this.toggleModal = this.toggleModal.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleTitleChange = this.handleTitleChange.bind(this);
		this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
    }

    componentDidMount() { // <2>
		client({method: 'GET', path: '/api/items'}).done(response => {
			this.setState({items: response.entity._embedded.items});
		});
	}

	toggleModal() {
		this.setState({isOpen:!this.state.isOpen})
	  }

	  handleTitleChange(event) {
		this.setState({newTitle: event.target.value});
	  }
	  handleDescriptionChange(event) {
		this.setState({newDescription: event.target.value});
	  }

	handleSubmit() {

		alert("Created new Item " + this.state.newTitle +': '+ this.state.newDescription);
		this.toggleModal();
		
		fetch('/items', {
			method: 'POST',
			body: JSON.stringify({title:this.state.newTitle, description:this.state.newDescription}),
			headers:{
				'Content-Type': 'application/json'
			}
		}).then(response => {
			if (response.status >= 200 && response.status < 300) {
				return response;
			  } else {
			   console.log('Somthing went wrong');
			  }
		}).catch(err => err);
		
	  }

    render(){	
		



		return (
			<div>
			<button onClick={this.toggleModal}>Add new item</button>
			<Modal
				isOpen={this.state.isOpen}
				onRequestClose={this.toggleModal}
				contentLabel="My dialog"
			>
				<div>Add new item</div>
				<form>
					<div>
					<input type="text" value={this.state.newTitle} onChange={this.handleTitleChange} placeholder = "Title" className="field"/>
					</div>
					<input type="text" value={this.state.newDescription} onChange={this.handleDescriptionChange} placeholder = "Description" className="field"/>

				</form>
				<button onClick={this.handleSubmit}>Submit</button>				
				<button onClick={this.toggleModal}>Cancel</button>
    	  </Modal>
			<ItemList items={this.state.items}/>
			</div>
		)
    }

}

class ItemList extends React.Component{
	render() {
		const items = this.props.items.map(item =>
			<Item key={item._links.self.href} item={item}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Title</th>
						<th>Description</th>
					</tr>
					{items}
				</tbody>
			</table>
		)
	}
}

class Item extends React.Component{
	render() {
		let s=this.props.item._links.self.href;
		return (
			<tr>
                <td>{s.substring(s.indexOf('items/')+6,s.length)}</td>
				<td>{this.props.item.description}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)

/*
// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {users: []};
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/api/users'}).done(response => {
			this.setState({users: response.entity._embedded.users});
		});
	}

	render() { // <3>
		return (
			<UserList users={this.state.users}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class UserList extends React.Component{
	render() {
		const users = this.props.users.map(user =>
			<User key={user._links.self.href} user={user}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Username</th>
						<th>Password</th>
						<th>Useless third one</th>
					</tr>
					{users}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class User extends React.Component{
	render() {
		return (
			<tr>
                <td>{this.props.user.username}</td>
				<td>{this.props.user.password}</td>
				<td>"test"</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]
*/