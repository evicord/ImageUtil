//
//  ImageUtil.m
//  panart
//
//  Created by zsly on 16/1/13.
//
//

#import "ImageUtil.h"

@implementation ImageUtil
-(void)toBinary:(CDVInvokedUrlCommand*)command
{
    NSString *path=command.arguments[0];
    NSError *error=nil;
    NSData *data=[NSData dataWithContentsOfFile:path options:NSDataReadingMappedIfSafe error:&error];
    CDVPluginResult*pluginResult=nil;
    if(error)
    {
        pluginResult= [CDVPluginResult resultWithStatus:CDVCommandStatus_IO_EXCEPTION messageAsString:error.localizedDescription];
    }
    else
    {
        pluginResult= [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArrayBuffer:data];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
