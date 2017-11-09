import React from 'react';
import { Alert, StyleSheet, Text, View, Button, Navigator } from 'react-native';
import { StackNavigator } from 'react-navigation';
import HomeScreen from './Components/Views/HomeScreen'
import SongListView from './Components/Views/SongListView'
import EditSongView from './Components/Views/EditSongView'
import NewSong from './Components/Views/NewSong'

const NavigApp = StackNavigator({
    Home: {
        screen: HomeScreen
    },
    Edit: {
        screen: EditSongView
    },
    ShowList: {
        screen: SongListView
    },
    Submit: {
        screen: NewSong
    }
});

export default class App extends React.Component {
    render() {
        return (
            <NavigApp />
        );
    }
}
