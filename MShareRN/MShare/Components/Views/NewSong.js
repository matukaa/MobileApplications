import React from 'react';
import { Alert, StyleSheet, Text, View, Navigator, TextInput, Button } from 'react-native';
import * as Communications from 'react-native-communications';

export default class HomeScreen extends React.Component {
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
        title: 'Submit'
    };

    render() {
        return (
            <View style={styles.container} >
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    placeholder="Title"
                    onChangeText={(value) => this.setState({ title: value })}
                />
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    placeholder="Artist"
                    onChangeText={(value) => this.setState({ artist: value })}
                />
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    placeholder="Album"
                    onChangeText={(value) => this.setState({ album: value })}
                />
                <TextInput
                    style={styles.textInputStyle}
                    underlineColorAndroid='rgba(0, 0, 0, 0)'
                    autoCorrect={false}
                    placeholder="Youtube link"
                    onChangeText={(value) => this.setState({ link: value })}
                />
                <Button
                    onPress={() => Communications.email(['mshare@gmail.com'], null, null, 'Song submission',
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