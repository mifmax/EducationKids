package home.maximv.utils;

/**
* @author Greg Milette &#60;<a href="mailto:gregorym@gmail.com">gregorym@gmail.com</a>&#62;
*
*/
public interface SpeechActivator
{
    /**
* listen for speech activation, when heard, call a {@link SpeechActivationListener}
* and stop listening
*/
    public void detectActivation();

    /**
* stop waiting for activation.
*/
    public void stop();
}
