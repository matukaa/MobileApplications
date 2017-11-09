import React from 'react';
import { Alert, StyleSheet, Text, View, Navigator } from 'react-native';
import ShowSongsButton from '../Buttons/ShowSongsButton'
import NewSongButton from '../Buttons/NewSongButton'

export default class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
    }
    static navigationOptions = {
        title: 'MShare'
    };

    render() {
        return (
            <View style={styles.container}>
                <ShowSongsButton navigation={this.props.navigation} />
                <NewSongButton navigation={this.props.navigation} />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    menuContainer: {
        backgroundColor: 'blue'
    },
    container: {
        flex: 1,
        justifyContent: 'flex-start',
        backgroundColor: '#fff',
    },
    menuText: {
        margin: 20,
        fontSize: 24,
        color: 'white'
    },
});