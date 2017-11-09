import React from 'react';
import { StyleSheet, Text, View, ListView, TextInput, Button } from 'react-native';
import * as Communications from 'react-native-communications';

const songs = [
    { title: 'Title1', artist: 'Artist1', album: 'Album1', link: 'www.yt.com/link1' },
    { title: 'Title2', artist: 'Artist2', album: 'Album2', link: 'www.yt.com/link2' },
    { title: 'Title3', artist: 'Artist3', album: 'Album3', link: 'www.yt.com/link3' },
    { title: 'Title4', artist: 'Artist4', album: 'Album4', link: 'www.yt.com/link4' },
]

export default class EditSongView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            artist: '',
            album: '',
            link: ''
        }
    }
    static navigationOptions = {
        title: 'Edit'
    };

    render() {
        const { params } = this.props.navigation.state;
        return (
            <View style={styles.container} >
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    value={params.song.title}
                    onChangeText={(value) => this.setState({ title: value })}
                />
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    value={params.song.artist}
                    onChangeText={(value) => this.setState({ artist: value })}
                />
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    value={params.song.album}
                    onChangeText={(value) => this.setState({ album: value })}
                />
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    value={params.song.link}
                    onChangeText={(value) => this.setState({ link: value })}
                />
                <Button
                    onPress={() => Communications.email(['mshare@gmail.com'], null, null, 'Edit song',
                        'Title: ' + this.state.title +
                        '\nArtist: ' + this.state.artist +
                        '\nAlbum: ' + this.state.album +
                        '\nLink: ' + this.state.link)}
                    title={"Send"}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        marginHorizontal: 20,
        marginTop: 30,
    },
    textInputStyle: {
        fontSize: 20,
        lineHeight: 30,
        height: 30,
        borderWidth: 1,
        marginVertical: 10
    },
});
